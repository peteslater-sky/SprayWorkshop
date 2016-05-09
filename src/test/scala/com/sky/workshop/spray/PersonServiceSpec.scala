package com.sky.workshop.spray

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.routing.HttpService

class PersonServiceSpec extends Specification with Specs2RouteTest with HttpService with PersonService {
  def actorRefFactory = system

  val personRespone = """{
                        |  "title": "Mr",
                        |  "forename": "Bobby",
                        |  "surname": "Bobbyson"
                        |}""".stripMargin

  "The Hello endpoint" should {
    "return a greeting" in {
      Get("/person") ~> personRoutes ~> check {
        responseAs[String] must be equalTo(personRespone)
      }
    }
  }
}
