package com.sky.workshop.spray

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.routing.HttpService
// import com.sky.workshop.spray.Hello.routes

class HelloSpec extends Specification with Specs2RouteTest with HttpService {
  def actorRefFactory = system

  val routes =
    path("hello") {
      get {
        complete {
          <h1>Say hello to spray</h1>
        }
      }
    }

  "The Hello endpoint" should {
    "return a greeting" in {
      Get("/hello") ~> routes ~> check {
        responseAs[String] must contain("Say hello")
      }
    }
  }
}
