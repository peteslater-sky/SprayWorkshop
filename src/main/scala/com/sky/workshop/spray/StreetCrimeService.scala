package com.sky.workshop.spray

import spray.routing.HttpService
import spray.http.MediaTypes._
import spray.json.DefaultJsonProtocol
import spray.httpx.unmarshalling._
import spray.httpx.marshalling._
import spray.client.pipelining._

case class CrimeLookup(
  latitude: String,
  longitude: String,
  month: String
)

object CrimeLookupJsonProtocol extends DefaultJsonProtocol {
  implicit val CrimeLookupJF = jsonFormat3(CrimeLookup)
}

case class Crime(
  category: String,
  persistent_id: String,
  location_type: String,
  location_subtype: String,
  id: Int,
  context: String,
  month: String
)

object CrimeJsonProtocol extends DefaultJsonProtocol {
  implicit val CrimeJF = jsonFormat7(Crime)
}

trait StreetCrimeService extends HttpService with RestClient {
  import CrimeJsonProtocol._
  import CrimeLookupJsonProtocol._
  import spray.httpx.SprayJsonSupport._

  val crimeUri = "http://data.police.uk/api/crimes-street/all-crime?lat=53.790397&lng=-1.530329&date=2016-02"

  val streetCrimeRoutes =
    path("streetCrime") {
      get {
        respondWithMediaType(`application/json`) {
          parameters('latitude, 'longitude, 'month) { (latitude, longitude, month) =>
            complete {
              // TODO: Make call to API and unmarshal to Crime objects before returning response
              Crime(
                category = "anti-social-behaviour",
                persistent_id = "",
                location_type = "Force",
                location_subtype = "",
                id = 12345,
                context = "",
                month = s"$month"
              )
            }
          }
        }
      } ~
      post {
        respondWithMediaType(`application/json`) {
          entity(as[CrimeLookup]) { crimeLookup =>
            val month = crimeLookup.month
            complete {
              // TODO: Make call to API and unmarshal to Crime objects before returning response
              Crime(
                category = "anti-social-behaviour",
                persistent_id = "",
                location_type = "Force",
                location_subtype = "",
                id = 12345,
                context = "",
                month = s"$month"
              )
            }
          }
        }
      }
    }
}
