package br.com.renanpass.flickr.api.parser

import br.com.renanpass.flickr.api.error.FlickrError
import br.com.renanpass.flickr.api.model.Photo
import org.specs2.Specification

import scala.io.Source

/**
  * @author rsouza on 31/12/16.
  */
class XmlParserSpec extends Specification{
  def is = s2"""
    This is a specification for the XmlParser

    it should parse sucessifuly a valid xml file ${sucessParseSpec}
    it should parse all the elements from the xml file ${xmlParseElementsSpec}
    it should return an flicker error if there is an error during parse ${invalidXmlSpec}
    """

  def sucessParseSpec = {
    val response = readXmlFromResource("/photos-list.xml")
    response.isRight must_==   true
  }

  def xmlParseElementsSpec = {
    val response = readXmlFromResource("/photos-list.xml")
    val photos: Seq[Photo] = response.right.get
    photos.size must beEqualTo(16)
  }

  def invalidXmlSpec = {
    val response = readXmlFromResource("/empty.xml")
    response.isLeft must_== true
  }

  def readXmlFromResource(fileName:String):Either[FlickrError, Seq[Photo]] = {
    val parser = new XmlParser
    val is = Source.fromInputStream(getClass().getResourceAsStream(fileName))
    val xml = is.mkString
    parser.parse(xml)
  }
}
