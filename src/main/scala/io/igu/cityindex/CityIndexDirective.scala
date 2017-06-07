package io.igu.cityindex

import io.igu.cityindex.exception.WsCallFailedException
import org.json4s.Formats

/**
  * Created by jackliddiard on 06/06/17.
  */
trait CityIndexDirective {

  def environment: String

  def client: WsClient

  protected def request(resource: String): WSRequest = client
    .url(s"$environment/TradingAPI$resource")
    .withHeaders("Content-Type" -> "application/json")

  protected def authenticaatedRequest(resource: String): (AuthenticationToken => WSRequest) = (authenticationToken) => request(resource)
    .withHeaders(
      "Session" -> authenticationToken.session,
      "UserName" -> authenticationToken.userName
    )


  def responseToObject[T <: AnyRef](response: WSResponse)(implicit formats: Formats, m: scala.reflect.Manifest[T]): T = response.status match {
    case 200 => response.json.camelizeKeys.extract[T]
    case 404 => throw new WsCallFailedException(404, "Not Found")
    case _ => throw WsCallFailedException(response.json)
  }

}
