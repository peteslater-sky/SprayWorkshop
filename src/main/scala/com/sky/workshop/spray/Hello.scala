package com.sky.workshop.spray

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem

object Hello extends App with SimpleRoutingApp {
  implicit val system = ActorSystem("my-system")

  val routes = {
    path("hello") {
      get {
        complete {
          <h1>Say hello to spray</h1>
        }
      }
    }
  }

  startServer(interface = "localhost", port = 8080) (routes)
}
