package br.com.renanpass.flickr.api

import br.com.renanpass.flickr.api.error._
import br.com.renanpass.flickr.api.http.HttpClient
import br.com.renanpass.flickr.api.model.Photo
import br.com.renanpass.flickr.api.parser.{FlickrMethods, ResponseParser, XmlParser}
import com.typesafe.config.ConfigFactory

/**
  * @author rsouza on 31/12/16.
  */
class FlickrClient(apiKey: String, baseUrl:String, parser:ResponseParser, httpClient: HttpClient) {

  def searchPhotos(tag: Option[String]): Either[ClientError, Seq[Photo]] = {
    val  tagText = tag.map("&tags=" + _).getOrElse("")

    val url = s"$baseUrl/?method=${FlickrMethods.photosSearch}&api_key=$apiKey$tagText"

    val response = httpClient.get(url)

    response match {
      case Left(l) => Left(new ClientError(l.error.toString))
      case Right(r) => {
        parser.parse(r.source.mkString) match {
          case Right(r) => Right(r)
          case Left(l) => Left(new ClientError(l.message))
        }
      }
    }

  }
}

object FlickrClient{

  private val httpClient = new HttpClient
  private val parser = new XmlParser
  private val baseUrl = "https://api.flickr.com/services/rest"
  private val apiKey = ConfigFactory.load.getString("flickr.api.key")

  def apply(): FlickrClient = new FlickrClient(apiKey, baseUrl, parser, httpClient)

}