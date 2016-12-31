package br.com.renanpass.flickr.api.http

import br.com.renanpass.flickr.api.error.GetError

import scala.io.Source

/**
  * @author rsouza on 31/12/16.
  */
class HttpClient {

  def get(url:String):Either[GetError, GetResponse] = {
    try{
      Right(new GetResponse(Source.fromURL(url)))
    }catch {
      case e: Exception => Left(new GetError(e))
    }
  }

}
