package io.igu.cityindex.client.authentication

import io.igu.cityindex.client.WsClient
import io.igu.cityindex.client.authentication.model.{LogOnRequest, LogOnResponse}
import io.igu.cityindex.exception.WsCallFailedException
import org.json4s.{DefaultFormats, FieldSerializer}

import scala.concurrent.{ExecutionContext, Future}

class AuthenticationClientImpl(client: WsClient)(implicit executionContext: ExecutionContext) extends AuthenticationClient {

  implicit val formats = DefaultFormats.preservingEmptyValues + FieldSerializer[LogOnRequest]()

  override def logOn(logOnRequest: LogOnRequest): Future[LogOnResponse] = client.url("https://ciapi.cityindex.com/TradingAPI/session")
    .withHeaders("Content-Type" -> "application/json")
    .post(logOnRequest)
    .map { response =>
      response.status match {
        case 200 => response.json.camelizeKeys.extract[LogOnResponse]
        case _ => throw WsCallFailedException(response.json)
      }
    }
}
