package io.igu.cityindex

import org.json4s.native.JsonParser
import org.json4s.{Formats, JValue}

import scala.concurrent.Future

class WsTestClient(urlMapper: PartialFunction[String, WSResponse]) extends WsClient {
  override def url(url: String): WSRequest = {

    new WSRequest {
      override def withHeaders(tuple: (String, String)*): WSRequest = this

      override def withQueryString(parameters: (String, String)*): WSRequest = this

      override def post[T <: AnyRef](body: T)(implicit formats: Formats): Future[WSResponse] = Future.successful(urlMapper.applyOrElse(url, (url: String) => throw new RuntimeException(s"Not Found $url")))

      override def get: Future[WSResponse] = Future.successful(urlMapper.applyOrElse(url, (url: String) => throw new RuntimeException(s"Not Found $url")))

      override def withOptQueryString(parameters: (String, Option[String])*): WSRequest = ???
    }

  }
}

object WsTestClient {
  def withRouter(urlMapper: PartialFunction[String, WSResponse]): WsClient = new WsTestClient(urlMapper)

  def respond(response: String): WSResponse = new WSResponse {
    override def body: String = response

    override def json: JValue = JsonParser.parse(response)

    override def status: Int = 200
  }
}