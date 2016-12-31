package br.com.renanpass.flickr.api.parser

import br.com.renanpass.flickr.api.error._
import br.com.renanpass.flickr.api.model._

sealed trait ResponseParser {
  def parse(xmlString: String): Either[FlickrError, Seq[Photo]]
}

class XmlParser extends ResponseParser {
  override def parse(xmlString: String): Either[FlickrError, Seq[Photo]] = ???
}
