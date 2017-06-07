package io.igu.cityindex.accountInformation

import io.igu.cityindex.{AuthenticationToken, CityIndexDirective}
import io.igu.cityindex.accountInformation.model.AccountInformationResponse
import org.json4s.{DefaultFormats, FieldSerializer}

import scala.concurrent.{ExecutionContext, Future}

trait ClientAndTradingAccountDirective {

  self: CityIndexDirective =>

  private implicit val formats = DefaultFormats.preservingEmptyValues + FieldSerializer[AccountInformationResponse]()

  private val endpoint = authenticaatedRequest("/useraccount/ClientAndTradingAccount")

  def clientAndTradingAccount(authenticationToken: AuthenticationToken)(implicit executionContext: ExecutionContext): Future[AccountInformationResponse] = endpoint(authenticationToken)
    .get
    .map(responseToObject[AccountInformationResponse])

}
