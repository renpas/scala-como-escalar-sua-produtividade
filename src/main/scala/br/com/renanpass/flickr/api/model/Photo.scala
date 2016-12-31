package br.com.renanpass.flickr.api.model

case class Photo(val id:Long, val owner:String, val title:String,	val farm:	Int) extends Media
