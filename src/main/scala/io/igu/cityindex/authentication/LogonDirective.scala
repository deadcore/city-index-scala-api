package io.igu.cityindex.authentication

import io.igu.cityindex.CityIndexDirective
import io.igu.cityindex.authentication.model.{LogOnRequest, LogOnResponse}
import org.json4s.{DefaultFormats, FieldSerializer}

import scala.concurrent.{ExecutionContext, Future}

trait LogonDirective {
  self: CityIndexDirective =>

  private implicit val formats = DefaultFormats.preservingEmptyValues + FieldSerializer[LogOnRequest]()

  private val endpoint = request("/session")

  def logOn(logOnRequest: LogOnRequest)(implicit executionContext: ExecutionContext): Future[LogOnResponse] = endpoint.post(logOnRequest).map(responseToObject[LogOnResponse])

}