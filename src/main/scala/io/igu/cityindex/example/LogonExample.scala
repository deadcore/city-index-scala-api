package io.igu.cityindex.example

import io.igu.cityindex.HttpJwsClient
import io.igu.cityindex.market.model.MarketSearchRequest
import org.scalatest.concurrent.{PatienceConfiguration, ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

object LogonExample extends App with ScalaFutures with PatienceConfiguration {
  private implicit val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global
  private implicit val defaultPatience = PatienceConfig(timeout = Span(10, Seconds), interval = Span(100, Millis))

  private val logger = LoggerFactory.getLogger(this.getClass)

  val cityIndexClient = new CityIndexClient(new HttpJwsClient())

  val authenticatedCityIndexClient = cityIndexClient.authenticate(args(0), args(1)).futureValue

  logger.info(s"Client trading account: ${authenticatedCityIndexClient.clientAndTradingAccount.futureValue}")


  val marketSearch = authenticatedCityIndexClient.marketSearch(MarketSearchRequest(
    cfdProductType = true,
    binaryProductType = false,
    includeOptions = false,
    query = "UK 100 CFD"
  )).futureValue

  val market = marketSearch.markets.head

  logger.info(s"Market Search: $market")

  val marketInformation = authenticatedCityIndexClient.information(market.marketId).futureValue

  logger.info(s"Market Information: $marketInformation")

  val ftsePrice = authenticatedCityIndexClient.price(market.marketId)

  ftsePrice.subscribe(price => logger.info(s"New Price: $price"))

}
