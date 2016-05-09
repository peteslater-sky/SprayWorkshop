package com.sky.workshop.spray

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem

object WebServiceApp extends App with SimpleRoutingApp with HelloService with PersonService with StreetCrimeService {
  implicit val system = ActorSystem("my-system")

  startServer(interface = "localhost", port = 8080) (helloRoutes ~ personRoutes ~ streetCrimeRoutes)
}
