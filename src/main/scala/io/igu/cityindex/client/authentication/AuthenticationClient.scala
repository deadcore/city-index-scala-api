package io.igu.cityindex.client.authentication

import io.igu.cityindex.client.authentication.model.LogOnRequest

import scala.concurrent.Future

trait AuthenticationClient {
  def logOn(logOnRequest: LogOnRequest): Future[_]
}
