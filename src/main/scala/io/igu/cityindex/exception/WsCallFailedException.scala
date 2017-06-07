package io.igu.cityindex.exception

import org.json4s.DefaultFormats
import org.json4s.JsonAST.JValue

class WsCallFailedException(httpStatus: Int, errorMessage: String, errorCode: Option[Int] = None) extends CityIndexException(s"[$httpStatus] $errorMessage") {

}

object WsCallFailedException {

  implicit val formats = DefaultFormats

  def apply(json: JValue): WsCallFailedException = new WsCallFailedException(
    httpStatus = (json \ "HttpStatus").extract[Int],
    errorMessage = (json \ "ErrorMessage").extract[String],
    errorCode = (json \ "ErrorCode").extractOpt[Int]
  )
}