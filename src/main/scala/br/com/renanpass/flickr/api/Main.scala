package br.com.renanpass.flickr.api

import br.com.renanpass.flickr.api.parser._
import com.typesafe.config.ConfigFactory

object Main extends App{
  val conf = ConfigFactory.load
  val apiKey: String = conf.getString("flickr.api.key")

  val client = new FlickrClient(apiKey)

  client.searchPhotos(Some("test")) match {
    case Left(l) => println("error calling flickf client: " + l.message)
    case Right(r) => {
      println("success")
      r.foreach{println}
    }
  }
}
