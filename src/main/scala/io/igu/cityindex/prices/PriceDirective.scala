package io.igu.cityindex.prices

import com.lightstreamer.ls_client._
import io.igu.cityindex.AuthenticationToken
import io.igu.cityindex.lightstreamer.{EmptyConnectionListener, HandyTableListenerBuilder}
import io.igu.cityindex.prices.model.Price
import org.slf4j.LoggerFactory
import rx.lang.scala.{Observable, Subscriber}

import scala.util.{Failure, Success}

trait PriceDirective {

  private val logger = LoggerFactory.getLogger(this.getClass)

  private lazy val client = new LSClient()

  private def extendedTableInfo(marketId: Int) = {
    val extendedTableInfo = new ExtendedTableInfo(
      Array(s"PRICE.$marketId"),
      "MERGE",
      Array("MarketId", "TickDate", "Bid", "Offer", "Price", "High", "Low", "Change", "Direction", "Delta", "ImpliedVolatility", "AuditId", "StatusSummary"),
      true
    )

    extendedTableInfo.setDataAdapter("PRICES")

    extendedTableInfo
  }

  def price(authenticationToken: AuthenticationToken)(marketId: Int): Observable[Price] = {

    Observable { (subscriber: Subscriber[Price]) =>

      val connectionInfo = new ConnectionInfo

      connectionInfo.user = authenticationToken.userName
      connectionInfo.password = authenticationToken.session
      connectionInfo.pushServerUrl = "https://push.cityindex.com"
      connectionInfo.adapter = "STREAMINGALL"

      client.openConnection(connectionInfo, EmptyConnectionListener)

      client.subscribeItems(extendedTableInfo(marketId), HandyTableListenerBuilder.empty.onUpdate(t))

      def t(i: Int, s: String, updateInfo: UpdateInfo): Unit = {
        Price(updateInfo) match {
          case Success(price) => subscriber.onNext(price)
          case Failure(exception) => logger.error(s"Error while parsing the price with exception: ${exception.getMessage}", exception)
        }
      }

    }
  }

}
