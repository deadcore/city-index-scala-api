package io.igu.cityindex.cfdMarket

import io.igu.cityindex.cfdMarket.model.{CfdMarketRequest, CfdMarketResponse}
import io.igu.cityindex.{AuthenticationToken, CityIndexDirective}
import org.json4s.{DefaultFormats, FieldSerializer}

import scala.concurrent.{ExecutionContext, Future}

trait CfdMarketsDirective {
  self: CityIndexDirective =>

  private implicit val formats = DefaultFormats.preservingEmptyValues + FieldSerializer[CfdMarketResponse]()

  private val endpoint = authenticaatedRequest("/cfd/markets")

  def listCfdMarkets(authenticationToken: AuthenticationToken)(cfdMarketRequest: CfdMarketRequest)(implicit executionContext: ExecutionContext): Future[CfdMarketResponse] = endpoint(authenticationToken)
    .withQueryString(
      "MarketName" -> cfdMarketRequest.marketName,
      "MarketCode" -> cfdMarketRequest.marketCode,
      "ClientAccountId" -> cfdMarketRequest.clientAccountId.toString,
      "MaxResults" -> cfdMarketRequest.maxResults.toString,
      "UseMobileShortName" -> cfdMarketRequest.useMobileShortName.toString,
      "IncludeOptions" -> cfdMarketRequest.includeOptions.toString,
      "TradingAccountId" -> cfdMarketRequest.tradingAccountId.toString
    )
    .get
    .map(responseToObject[CfdMarketResponse])

}