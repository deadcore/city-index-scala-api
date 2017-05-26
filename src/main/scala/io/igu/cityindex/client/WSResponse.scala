package io.igu.cityindex.client

import org.json4s.JsonAST.JValue

trait WSResponse {
  def body: String

  def status: Int

  def json: JValue
}
