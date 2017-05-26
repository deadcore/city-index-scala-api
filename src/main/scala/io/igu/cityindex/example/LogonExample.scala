package io.igu.cityindex.example

import io.igu.cityindex.client.accountInformation.AccountInformationClientImpl
import io.igu.cityindex.client.authentication.AuthenticationClientImpl
import io.igu.cityindex.client.authentication.model.LogOnRequest
import io.igu.cityindex.client.{AuthenticationToken, HttpJwsClient}
import org.scalatest.concurrent.{PatienceConfiguration, ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}

import scala.concurrent.ExecutionContext

object LogonExample extends App with ScalaFutures with PatienceConfiguration {

  implicit val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global
  implicit val defaultPatience = PatienceConfig(timeout = Span(1, Seconds), interval = Span(100, Millis))

  val wsClient = new HttpJwsClient

  val authenticationClient = new AuthenticationClientImpl(wsClient)
  val accountInformationClient = new AccountInformationClientImpl(wsClient)

  val login = authenticationClient.logOn(LogOnRequest(
    password = args(1),
    appVersion = "1",
    appComments = "",
    userName = args(0),
    appKey = ""
  )).futureValue

  val token = AuthenticationToken(login.session, args(0))
  val accountInfo = accountInformationClient.getClientAndTradingAccount(token).futureValue

  println(accountInformationClient.getChartingEnabled(token, accountInfo.clientAccountId).futureValue)
}
