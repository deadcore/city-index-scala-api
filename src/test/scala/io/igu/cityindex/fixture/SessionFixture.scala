package io.igu.cityindex.fixture

import scala.io.Source

object SessionFixture {

  private def load(resourcePath: String) = Source.fromURL(getClass.getResource(resourcePath)).mkString

  val validSession: String = load("/fixture/session/valid_session_result.json")

}
