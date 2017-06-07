package io.igu.cityindex

import org.json4s.JValue

/**
  * Created by jackliddiard on 06/06/17.
  */
trait WSResponse {
  def body: String

  def status: Int

  def json: JValue
}
