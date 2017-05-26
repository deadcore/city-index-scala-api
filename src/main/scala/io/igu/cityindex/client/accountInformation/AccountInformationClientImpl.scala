package io.igu.cityindex.client.accountInformation

import io.igu.cityindex.client.accountInformation.model.AccountInformationResponse
import io.igu.cityindex.client.authentication.model.LogOnRequest
import io.igu.cityindex.client.{AuthenticationToken, WSRequest, WsClient}
import io.igu.cityindex.exception.WsCallFailedException
import org.json4s.{DefaultFormats, FieldSerializer}

import scala.concurrent.{ExecutionContext, Future}

class AccountInformationClientImpl(client: WsClient)(implicit executionContext: ExecutionContext) extends AccountInformationClient {

  implicit val formats = DefaultFormats.preservingEmptyValues + FieldSerializer[LogOnRequest]()

  override def getClientAndTradingAccount(authenticationToken: AuthenticationToken): Future[AccountInformationResponse] = request(authenticationToken, "/ClientAndTradingAccount")
    .get
    .map { response =>
      response.status match {
        case 200 => response.json.camelizeKeys.extract[AccountInformationResponse]
        case _ => throw WsCallFailedException(response.json)
      }
    }

  override def getChartingEnabled(authenticationToken: AuthenticationToken, userId: Int): Future[Boolean] = request(authenticationToken, s"/$userId/ChartingEnabled")
    .get
    .map { response =>
      response.status match {
        case 200 => response.body.toBoolean
        case _ => throw WsCallFailedException(response.json)
      }
    }

  private def request(authenticationToken: AuthenticationToken, resource: String): WSRequest = client.url(s"https://ciapi.cityindex.com/TradingAPI/useraccount$resource")
    .withHeaders(
      "Session" -> authenticationToken.session,
      "UserName" -> authenticationToken.userName
    )

}