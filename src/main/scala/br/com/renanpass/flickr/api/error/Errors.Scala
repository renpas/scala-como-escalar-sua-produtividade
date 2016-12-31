package br.com.renanpass.flickr.api.error

sealed abstract ApiError(val message:String)

abstract class FlickrError(message:String) extends ApiError(message)

class FlickrUnknownError(message:String) extends FlickrError(message)

class FlickrKnownError(message:String) extends FlickrError(message)

class ClientError(message:String) extends ApiError(message)
