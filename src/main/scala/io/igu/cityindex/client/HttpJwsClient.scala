package io.igu.cityindex.client

import org.json4s.native.JsonMethods
import org.json4s.native.Serialization.write
import org.json4s.{Formats, JsonAST}

import scala.concurrent.{ExecutionContext, Future, blocking}
import scalaj.http.{Http, HttpRequest, HttpResponse}

class HttpJwsClient(implicit executionContext: ExecutionContext) extends WsClient {
  def url(url: String): WSRequest = new HttpJWsRequest(Http(url))

}


class HttpJWsRequest(http: HttpRequest)(implicit executionContext: ExecutionContext) extends WSRequest {
  override def withHeaders(tuple: (String, String)): WSRequest = new HttpJWsRequest(http.header(tuple._1, tuple._2))

  override def post[T <: AnyRef](body: T)(implicit formats: Formats): Future[WSResponse] = Future {
    blocking {
      http.postData(write(body)(formats)).asString
    }
  }

  implicit def toWSResponse(response: HttpResponse[String]): WSResponse = new WSResponse {
    override def json: JsonAST.JValue = JsonMethods.parse(response.body)

    override def status: Int = response.code
  }
}