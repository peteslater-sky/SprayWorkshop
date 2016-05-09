package com.sky.workshop.spray

import spray.routing.HttpService
import spray.http.MediaTypes._

import spray.json.DefaultJsonProtocol
import spray.httpx.unmarshalling._
import spray.httpx.marshalling._

case class Person(title: String, forename: String, surname: String)

object PersonJsonProtocol extends DefaultJsonProtocol {
  implicit val PersonJF = jsonFormat3(Person)
}

import PersonJsonProtocol._
import spray.httpx.SprayJsonSupport._

trait PersonService extends HttpService {
  val personRoutes =
    path("person") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            Person("Mr", "Bobby", "Bobbyson")
          }
        }
      }
    }
}
