package com.sky.workshop.spray

import spray.routing.HttpService

trait HelloService extends HttpService {
  val helloRoutes =
    path("hello") {
      get {
        complete {
          <h1>Say hello to spray</h1>
        }
      }
    }
}
