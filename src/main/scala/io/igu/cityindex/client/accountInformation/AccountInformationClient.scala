package io.igu.cityindex.client.accountInformation

import io.igu.cityindex.client.AuthenticationToken
import io.igu.cityindex.client.accountInformation.model.AccountInformationResponse

import scala.concurrent.Future

trait AccountInformationClient {
  def getClientAndTradingAccount(authenticationToken: AuthenticationToken): Future[AccountInformationResponse]

  def getChartingEnabled(authenticationToken: AuthenticationToken, userId: Int): Future[Boolean]
}
