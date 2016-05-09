package com.sky.workshop.spray

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.routing.HttpService

class HelloServiceSpec extends Specification with Specs2RouteTest with HttpService with HelloService {
  def actorRefFactory = system

  "The Hello endpoint" should {
    "return a greeting" in {
      Get("/hello") ~> helloRoutes ~> check {
        responseAs[String] must contain("Say hello")
      }
    }
  }
}
