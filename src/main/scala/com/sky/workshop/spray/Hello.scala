package com.sky.workshop.spray

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem

object Hello extends App with SimpleRoutingApp with HelloService {
  implicit val system = ActorSystem("my-system")

  startServer(interface = "localhost", port = 8080) (route)
}
