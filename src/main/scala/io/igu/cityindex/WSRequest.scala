package io.igu.cityindex

import org.json4s.Formats

import scala.concurrent.Future

/**
  * Created by jackliddiard on 06/06/17.
  */
trait WSRequest {
  def withHeaders(tuple: (String, String)*): WSRequest

  def withQueryString(parameters: (String, String)*): WSRequest

  def withOptQueryString(parameters: (String, Option[String])*): WSRequest

  def post[T <: AnyRef](body: T)(implicit formats: Formats): Future[WSResponse]

  def get: Future[WSResponse]
}
