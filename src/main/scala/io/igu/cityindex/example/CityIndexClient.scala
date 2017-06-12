package io.igu.cityindex.example

import io.igu.cityindex.authentication.LogonDirective
import io.igu.cityindex.authentication.model.LogOnRequest
import io.igu.cityindex.{AuthenticationToken, CityIndexDirective, WsClient}

import scala.concurrent.{ExecutionContext, Future}

class CityIndexClient(wsClient: WsClient) extends CityIndexDirective with LogonDirective {

  def environment = "https://ciapi.cityindex.com"

  def client: WsClient = wsClient

  def authenticate(username: String, password: String)(implicit executionContext: ExecutionContext): Future[AuthenticatedCityIndexClient] = {
    super.logOn(LogOnRequest(username, password)).map { logonResponse =>
      val authenticationToken = AuthenticationToken(logonResponse.session, username)
      new AuthenticatedCityIndexClient(authenticationToken, environment, client)
    }
  }

}