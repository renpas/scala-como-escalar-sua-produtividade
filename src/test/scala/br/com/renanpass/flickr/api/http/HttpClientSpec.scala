package br.com.renanpass.flickr.api.http;

import br.com.renanpass.flickr.api.error.GetError
import org.specs2.Specification;

/**
 * @author rsouza on 31/12/16.
 */
class HttpClientSpec extends Specification {

    def is = s2"""

        This is a specification for the XmlParser

        It should be ok if te response has a body ${responseWithBodySpec}
        It should be left with wrong Url ${invalidUrlSpec}
      """


  def responseWithBodySpec =  new HttpClient().get("https://httpbin.org/get") must beRight[GetResponse]

  def invalidUrlSpec = new HttpClient().get("bla-bla") must beLeft[GetError]
}
