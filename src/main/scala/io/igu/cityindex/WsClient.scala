package io.igu.cityindex

/**
  * Created by jackliddiard on 06/06/17.
  */
trait WsClient {
  def url(url : String): WSRequest
}
