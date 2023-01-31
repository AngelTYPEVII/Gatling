import Request._
import io.gatling.http.Predef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps
class LoadTest extends Simulation{
  val httpConf=http.baseUrl("https://demoqa.com/swagger/")

  val snc = scenario("First") randomSwitch(
    (100,account)
  )

  setUp(snc.inject(constantUsersPerSec(100)during(20 seconds))
    .protocols(httpConf))
}