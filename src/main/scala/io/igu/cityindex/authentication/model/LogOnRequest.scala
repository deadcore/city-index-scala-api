package io.igu.cityindex.authentication.model

case class LogOnRequest(userName: String,
                        password: String,
                        appVersion: Option[String] = None,
                        appComments: Option[String] = None,
                        appKey: Option[String] = None)

object LogOnRequest {
  def apply(password: String, userName: String): LogOnRequest = new LogOnRequest(userName = userName, password = password)
}