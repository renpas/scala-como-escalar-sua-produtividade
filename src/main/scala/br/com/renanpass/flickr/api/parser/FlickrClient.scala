package br.com.renanpass.flickr.api.parser

import br.com.renanpass.flickr.api.error._
import br.com.renanpass.flickr.api.model.Photo
import br.com.renanpass.flickr.api.http.HttpClient

/**
  * @author rsouza on 31/12/16.
  */
class FlickrClient(apiKey: String) {

  def searchPhotos(tag: Option[String]): Either[ClientError, Seq[Photo]] = {
    val method = FlickrMethods.photosSearch
    val  tagText = tag.map("&tags=" + _).getOrElse("")

    val url = s"https://api.flickr.com/services/rest/?method=$method&api_key=$apiKey$tagText"

    val httpClient = new HttpClient
    val response = httpClient.get(url)

    response match {
      case Left(l) => Left(new ClientError(l.error.toString))
      case Right(r) => {
        new XmlParser().parse(r.source.mkString) match {
          case Right(r) => Right(r)
          case Left(l) => Left(new ClientError(l.message))
        }
      }
    }

  }
}
