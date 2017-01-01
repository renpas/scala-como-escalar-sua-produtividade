package br.com.renanpass.flickr.api

object Main extends App{
  val client = FlickrClient()

  client.searchPhotos(Some("test")) match {
    case Left(l) => println("error calling flickr client: " + l.message)
    case Right(r) => r.foreach{println}
  }
}
