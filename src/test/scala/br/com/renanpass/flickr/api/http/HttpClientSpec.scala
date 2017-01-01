package br.com.renanpass.flickr.api.http

import br.com.renanpass.flickr.api.error.GetError
import org.specs2.Specification

/**
 * @author rsouza on 31/12/16.
 */
class HttpClientSpec extends Specification {

    val httpClient = new HttpClient

    def is = s2"""

        This is a specification for the XmlParser

        It should be ok if the response has a body ${httpClient.get("https://httpbin.org/get") must beRight[GetResponse]}
        It should be left with wrong Url ${httpClient.get("bla-bla") must beLeft[GetError]}
      """

}
