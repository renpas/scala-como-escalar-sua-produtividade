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

    it should parse successfully a valid xml file ${successParseSpec}
    it should parse all the elements from the xml file ${xmlParseElementsSpec}
    it should parse id from the photo ${xmlParseIdSpec}
    it should parse farm from the photo ${xmlParseFarmSpec}
    it should parse owner from the photo ${xmlParseOwnerSpec}
    it should parse title from the photo ${xmlParseTitleSpec}
    it should return an flicker error if there is an error during parse ${invalidXmlSpec}
    """

  def successParseSpec = {
    val response = readXmlFromResource("/photos-list.xml")
    response.isRight must beTrue
  }

  def xmlParseElementsSpec = {
    val response = readXmlFromResource("/photos-list.xml")
    val photos: Seq[Photo] = response.right.get
    photos.size must beEqualTo(16)
  }

  def xmlParseIdSpec = {
    val photos = readPhotoFromResource("/photos-list.xml")

    photos(0).id must beEqualTo(31007929530L)
  }

  def xmlParseFarmSpec = {
    val photos = readPhotoFromResource("/photos-list.xml")

    photos(0).farm must beEqualTo(5638)
  }

  def xmlParseOwnerSpec = {
    val photos = readPhotoFromResource("/photos-list.xml")

    photos(0).owner must beEqualTo("45036157@N04")
  }

  def xmlParseTitleSpec = {
    val photos = readPhotoFromResource("/photos-list.xml")

    photos(0).title must beEqualTo("Skills Matter Conference")
  }

  def invalidXmlSpec = {
    val response = readXmlFromResource("/empty.xml")
    response.isLeft must beTrue
  }

  def readXmlFromResource(fileName:String):Either[FlickrError, Seq[Photo]] = {
    val parser = new XmlParser
    val is = Source.fromInputStream(getClass().getResourceAsStream(fileName))
    val xml = is.mkString
    parser.parse(xml)
  }

  def readPhotoFromResource(fileName:String):Seq[Photo] = {
    readXmlFromResource(fileName).right.get
  }
}
