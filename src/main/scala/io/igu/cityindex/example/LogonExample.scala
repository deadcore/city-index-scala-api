package io.igu.cityindex.example

import io.igu.cityindex.accountInformation.ClientAndTradingAccountDirective
import io.igu.cityindex.authentication.LogonDirective
import io.igu.cityindex.authentication.model.LogOnRequest
import io.igu.cityindex.market.model.MarketSearchRequest
import io.igu.cityindex.market.{MarketInformationDirective, MarketSearchDirective}
import io.igu.cityindex.prices.PriceDirective
import io.igu.cityindex.{AuthenticationToken, CityIndexDirective, HttpJwsClient, WsClient}
import org.scalatest.concurrent.{PatienceConfiguration, ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

object LogonExample extends App with ScalaFutures with PatienceConfiguration {
  private implicit val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global
  private implicit val defaultPatience = PatienceConfig(timeout = Span(10, Seconds), interval = Span(100, Millis))

  private val logger = LoggerFactory.getLogger(this.getClass)

  val wsClient = new HttpJwsClient

  val cityIndexClient = new CityIndexDirective with LogonDirective with MarketSearchDirective with ClientAndTradingAccountDirective with MarketInformationDirective with PriceDirective {
    def environment = "https://ciapi.cityindex.com"

    def client: WsClient = wsClient
  }

  val logOnResponse = cityIndexClient.logOn(LogOnRequest(args(1), args(0))).futureValue
  val authenticationToken = AuthenticationToken(logOnResponse.session, args(0))

  logger.info(s"Authentication Token : $authenticationToken")


  logger.info(s"Client trading account: ${cityIndexClient.clientAndTradingAccount(authenticationToken).futureValue}")


  val marketSearch = cityIndexClient.marketSearch(authenticationToken)(MarketSearchRequest(
    cfdProductType = true,
    binaryProductType = false,
    includeOptions = false,
    query = "UK 100 CFD"
  )).futureValue

  val market = marketSearch.markets.head

  logger.info(s"Market Search: $market")

  val marketInformation = cityIndexClient.information(authenticationToken)(market.marketId).futureValue

  logger.info(s"Market Information: $marketInformation")

  val ftsePrice = cityIndexClient.price(authenticationToken)(market.marketId)


  ftsePrice.subscribe(price => logger.info(s"New Price: $price"))

}
