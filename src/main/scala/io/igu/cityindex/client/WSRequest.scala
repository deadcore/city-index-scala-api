package io.igu.cityindex.client

import org.json4s.Formats

import scala.concurrent.Future

trait WSRequest {
  def withHeaders(tuple: (String, String)): WSRequest

  def withHeaders(tuple: (String, String)*): WSRequest

  def post[T <: AnyRef](body: T)(implicit formats: Formats): Future[WSResponse]

  def get: Future[WSResponse]
}
