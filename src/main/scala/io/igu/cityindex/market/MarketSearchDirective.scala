package io.igu.cityindex.market

import io.igu.cityindex.market.model.{MarketSearchRequest, MarketSearchResponse}
import io.igu.cityindex.{AuthenticationToken, CityIndexDirective}
import org.json4s.{DefaultFormats, FieldSerializer}

import scala.concurrent.{ExecutionContext, Future}

trait MarketSearchDirective {
  self: CityIndexDirective =>

  private implicit val formats = DefaultFormats.preservingEmptyValues + FieldSerializer[MarketSearchResponse]()

  private val endpoint = authenticaatedRequest("/market/search")

  def marketSearch(authenticationToken: AuthenticationToken)(marketSearchRequest: MarketSearchRequest)(implicit executionContext: ExecutionContext): Future[MarketSearchResponse] = endpoint(authenticationToken)
    .withQueryString(
      "SearchByMarketCode" -> marketSearchRequest.searchByMarketCode.toString,
      "SearchByMarketName" -> marketSearchRequest.searchByMarketName.toString,
      "SpreadProductType" -> marketSearchRequest.spreadProductType.toString,
      "CfdProductType" -> marketSearchRequest.cfdProductType.toString,
      "BinaryProductType" -> marketSearchRequest.binaryProductType.toString,
      "IncludeOptions" -> marketSearchRequest.includeOptions.toString,
      "Query" -> marketSearchRequest.query,
      "MaxResults" -> marketSearchRequest.maxResults.toString,
      "UseMobileShortName" -> marketSearchRequest.useMobileShortName.toString
    ).withOptQueryString(
      "TradingAccountId" -> marketSearchRequest.tradingAccountId.map(_.toString)
    )
    .get
    .map(responseToObject[MarketSearchResponse])

}
