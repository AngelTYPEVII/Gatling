import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.util.UUID

object Request {
  val account =
    exec(
      http("post")
        .post("/Account/v1/Authorized")
        .check(status.is(200))
    )
  val getUser = exec(
    session => session.set("UUID", UUID.randomUUID().toString)
  ).exec(
    http("get")
      .get("/Account/v1/User/${UUID}")
      .check(status.is(200))
  )
  val deleteUser = exec(
    session => session.set("UUID", UUID.randomUUID().toString)
  ).exec(
    http("delete")
      .delete("/Account/v1/User/{UUID}")
      .check(status.is(200))
  )
  val postUser = exec(
    session => session.set("userName", UUID.randomUUID().toString)
  ).exec(
    session => session.set("password", UUID.randomUUID().toString)
  ).exec(
    http( "new user")
      .post("/Account/v1/User")
      .body(ElFileBody("body.json")).asJson
      .check(status.is(200))
  )
}
