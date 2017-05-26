package io.igu.cityindex.client

import org.json4s.{CustomSerializer, DefaultFormats, JObject, JValue}

abstract class Deserializer[A: Manifest](ser: JObject => A) extends CustomSerializer[A](_ => ( {
  case json: JObject => ser(json)
}, {
  case _ => throw new UnsupportedOperationException("Operation is not supported to serialise class to json")
}))

object Deserializer {
  private implicit val formats = DefaultFormats

  def toOptionIfBlank(str: String): Option[String] = if (str.isEmpty) None else Some(str)

  def jvalueToString(jvalue: JValue): String = jvalue.extract[String]
  def jvalueToOptionalString(jvalue: JValue): Option[String] = jvalue.extractOpt[String].flatMap(toOptionIfBlank)

}