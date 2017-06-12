package io.igu.cityindex.example

import io.igu.cityindex.accountInformation.ClientAndTradingAccountDirective
import io.igu.cityindex.accountInformation.model.AccountInformationResponse
import io.igu.cityindex.market.model.{MarketInformationResponse, MarketSearchRequest, MarketSearchResponse}
import io.igu.cityindex.market.{MarketInformationDirective, MarketSearchDirective}
import io.igu.cityindex.prices.PriceDirective
import io.igu.cityindex.prices.model.Price
import io.igu.cityindex.{AuthenticationToken, CityIndexDirective, WsClient}
import rx.lang.scala.Observable

import scala.concurrent.{ExecutionContext, Future}

class AuthenticatedCityIndexClient(authenticationToken: AuthenticationToken, inEnvironment: String, inClient: WsClient) extends CityIndexDirective with MarketSearchDirective with ClientAndTradingAccountDirective with MarketInformationDirective with PriceDirective {

  override def environment: String = inEnvironment

  override def client: WsClient = inClient

  def marketSearch(marketSearchRequest: MarketSearchRequest)(implicit executionContext: ExecutionContext): Future[MarketSearchResponse] = super.marketSearch(authenticationToken)(marketSearchRequest)

  def clientAndTradingAccount(implicit executionContext: ExecutionContext): Future[AccountInformationResponse] = super.clientAndTradingAccount(authenticationToken)

  def information(marketId: Int)(implicit executionContext: ExecutionContext): Future[MarketInformationResponse] = super.information(authenticationToken)(marketId)

  def price(marketId: Int)(implicit executionContext: ExecutionContext): Observable[Price] = super.price(authenticationToken)(marketId)


}