package br.com.renanpass.flickr.api

import br.com.renanpass.flickr.api.error.{ClientError, FlickrUnknownError, GetError}
import br.com.renanpass.flickr.api.http.{GetResponse, HttpClient}
import br.com.renanpass.flickr.api.model.Photo
import br.com.renanpass.flickr.api.parser.{ResponseParser, XmlParser}
import org.specs2.Specification
import org.specs2.mock._

import scala.io.{BufferedSource, Codec, Source}

/**
  * @author rsouza on 01/01/17.
  */
class FlickrClientSpec extends Specification with Mockito{

  val mockHttpClient = mock[HttpClient]
  val mockParser = mock[ResponseParser]
  val mockGetResponse = mock[GetResponse]
  val mockSource = mock[BufferedSource]
  mockGetResponse.source answers {(i) => mockSource}
  mockSource.mkString answers {i => ""}

  def is = s2"""
      This is a specification for the FlickrClient

      It should return a Client error if there is an error during the api call ${apiCallErrorSpec}
      It should return a Client error if there is an error during parser ${parserErrorSpec}
      It should return a Sequence of photo if the parse its ok ${parseOkSpec}
    """

  def apiCallErrorSpec = {
    val client = new FlickrClient("key", "", mockParser, mockHttpClient)
    val url: String = "/?method=flickr.photos.search&api_key=key"
    mockHttpClient.get(anyString) answers { i => Left(new GetError(new Exception("io"))) }
    val resp = client.searchPhotos(None)
    resp must beLeft[ClientError]
  }

  def parserErrorSpec = {
    val client = new FlickrClient("key", "", mockParser, mockHttpClient)
    mockHttpClient.get(anyString) answers { i => Right(mockGetResponse) }
    mockParser.parse(anyString) answers { i => Left(new FlickrUnknownError("Error"))}
    val resp = client.searchPhotos(None)
    resp must beLeft[ClientError]
  }

  def parseOkSpec = {
    val client = new FlickrClient("key", "", new XmlParser, mockHttpClient)
    val is = Source.fromInputStream(getClass().getResourceAsStream("/photos-list.xml"))
    mockHttpClient.get(anyString) answers { i => Right(new GetResponse(is)) }
    val resp = client.searchPhotos(None)
    resp must beRight[Seq[Photo]]
  }
}
