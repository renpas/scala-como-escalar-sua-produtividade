package br.com.renanpass.flickr.api.parser

import br.com.renanpass.flickr.api.model._

trait ResponseParser {
  def parse(xmlString: String): Seq[Photo]
}
