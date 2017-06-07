package io.igu.cityindex.market

import io.igu.cityindex.market.model.MarketInformationResponse
import io.igu.cityindex.{AuthenticationToken, CityIndexDirective}
import org.json4s.{DefaultFormats, FieldSerializer}

import scala.concurrent.{ExecutionContext, Future}

trait MarketInformationDirective {
  self: CityIndexDirective =>

  private implicit val formats = DefaultFormats.preservingEmptyValues + FieldSerializer[MarketInformationResponse]()

  private val endpoint = (marketId: Int) => authenticaatedRequest(s"/market/$marketId/information")

  def information(authenticationToken: AuthenticationToken)(marketId: Int)(implicit executionContext: ExecutionContext): Future[MarketInformationResponse] = endpoint(marketId)(authenticationToken)
    .get
    .map(responseToObject[MarketInformationResponse])

}
