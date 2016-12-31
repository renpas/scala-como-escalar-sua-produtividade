package br.com.renanpass.flickr.api

import br.com.renanpass.flickr.api.parser._
import com.typesafe.config.ConfigFactory

object Main extends App{
  val conf = ConfigFactory.load
  println(conf.getString("flickr.api.key"))
}
