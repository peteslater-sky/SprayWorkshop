package com.sky.workshop.spray

import akka.actor.{ActorRefFactory, ActorSystem}
import akka.util.Timeout
import spray.client.pipelining._
import spray.http._
import spray.httpx.unmarshalling._
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}


trait RestClient {

  implicit val system: ActorSystem
  implicit lazy val executionContext: ExecutionContext = system.dispatcher

  def doSendReceive(implicit refFactory: ActorRefFactory, executionContext: ExecutionContext): SendReceive = sendReceive

  def webGet[Rs: FromResponseUnmarshaller](path: String): Future[Rs] = {
    Get(path) ~> pipeline
  }

  def webPost[Rs: FromResponseUnmarshaller](req: HttpRequest): Future[Rs] = {
    req ~> pipeline
  }

  def pipeline[T: FromResponseUnmarshaller]: HttpRequest => Future[T] = {

    implicit val timeout: Timeout = Timeout(2 seconds)
    (
      doSendReceive
        ~> unmarshal[T]
      )
  }
}
