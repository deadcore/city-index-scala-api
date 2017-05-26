package io.igu.cityindex.client

trait WsClient {
  def url(url : String): WSRequest
}