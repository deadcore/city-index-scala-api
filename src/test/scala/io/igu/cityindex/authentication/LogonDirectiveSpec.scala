package io.igu.cityindex.authentication

import io.igu.cityindex.authentication.model.LogOnRequest
import io.igu.cityindex.fixture.SessionFixture
import io.igu.cityindex.{CityIndexDirective, WsClient, WsTestClient}
import org.scalatest.concurrent.{PatienceConfiguration, ScalaFutures}
import org.scalatest.{MustMatchers, OptionValues, WordSpec}

import scala.concurrent.ExecutionContext

class LogonDirectiveSpec extends WordSpec with MustMatchers with OptionValues with ScalaFutures with PatienceConfiguration {

  private implicit val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  "LogonDirective" should {
    "logOn(LogonRequest)" should {
      "perform authentication" in withLogonDirective { directive =>
        val x = directive.logOn(LogOnRequest("", "")).futureValue
      }
    }
  }

  private def withLogonDirective[T](block: LogonDirective => T): T = withWsClient { wsClient =>
    val directive = new CityIndexDirective with LogonDirective {
      def environment = ""
      def client: WsClient = wsClient
    }

    block(directive)
  }

  private def withWsClient[T](block: WsClient => T) = {
    val wsClient = WsTestClient.withRouter {
      case "/TradingAPI/session" => WsTestClient.respond(SessionFixture.validSession)
    }

    block(wsClient)
  }

}
