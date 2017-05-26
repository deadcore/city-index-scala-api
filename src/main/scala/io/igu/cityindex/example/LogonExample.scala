package io.igu.cityindex.example

import io.igu.cityindex.client.HttpJwsClient
import io.igu.cityindex.client.authentication.AuthenticationClientImpl
import io.igu.cityindex.client.authentication.model.LogOnRequest

import scala.concurrent.duration.DurationDouble
import scala.concurrent.{Await, ExecutionContext}

object LogonExample extends App {

  implicit val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global

  val client = new HttpJwsClient

  val authenticationClient = new AuthenticationClientImpl(client)

  val response = authenticationClient.logOn(LogOnRequest(
    password = "",
    appVersion = "1",
    appComments = "",
    userName = "",
    appKey = ""
  ))

  val value = Await.result(response, 2 seconds)

  println("Value from response: " + value)

}
