package io.igu.cityindex.prices

import com.lightstreamer.ls_client._
import io.igu.cityindex.AuthenticationToken
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

      client.openConnection(connectionInfo, new ConnectionListener {
        override def onFailure(e: PushServerException): Unit = logger.info(s"onFailure: $e")

        override def onFailure(e: PushConnException): Unit = logger.info(s"onFailure: $e")

        override def onActivityWarning(b: Boolean): Unit = logger.info(s"onActivityWarning: $b")

        override def onDataError(e: PushServerException): Unit = logger.info(s"onDataError: $e")

        override def onSessionStarted(b: Boolean): Unit = logger.info(s"onSessionStarted: $b")

        override def onClose(): Unit = logger.info(s"onClose")

        override def onEnd(i: Int): Unit = logger.info(s"onEnd")

        override def onConnectionEstablished(): Unit = logger.info(s"onConnectionEstablished")

        override def onNewBytes(l: Long): Unit = logger.info(s"onNewBytes :$l")
      })

      client.subscribeItems(extendedTableInfo(marketId), new HandyTableListener {
        override def onRawUpdatesLost(i: Int, s: String, i1: Int): Unit = logger.info(s"onRawUpdatesLost: $i, $s, $i1")

        override def onUnsubscrAll(): Unit = logger.info("onUnsubscrAll")

        override def onUnsubscr(i: Int, s: String): Unit = logger.info(s"onUnsubscr, : $i, $s")

        override def onSnapshotEnd(i: Int, s: String): Unit = logger.info(s"onSnapshotEnd, : $i, $s")

        override def onUpdate(i: Int, s: String, updateInfo: UpdateInfo): Unit = {
          Price(updateInfo) match {
            case Success(price) => subscriber.onNext(price)
            case Failure(exception) => logger.error(s"Error while parsing the price with exception: ${exception.getMessage}", exception)
          }
        }
      })
    }
  }

}
