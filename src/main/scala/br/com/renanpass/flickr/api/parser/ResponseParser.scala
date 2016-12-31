package br.com.renanpass.flickr.api.parser

import br.com.renanpass.flickr.api.error.FlickrError
import br.com.renanpass.flickr.api.model.Photo

import scala.xml.XML

sealed trait ResponseParser {
  def parse(xmlString: String): Either[FlickrError, Seq[Photo]]
}

class XmlParser extends ResponseParser {
  override def parse(xmlString: String): Either[FlickrError, Seq[Photo]] = {
    val xmlResp = XML.loadString(xmlString)

    val photos = xmlResp \\ "photo" map { p =>
      Photo(
        (p \ "@id" text).toLong,
        p \ "@owner" text,
        p \ "@title" text,
        (p \ "@server" text).toInt
      )
    }

    Right(photos)
  }
}
