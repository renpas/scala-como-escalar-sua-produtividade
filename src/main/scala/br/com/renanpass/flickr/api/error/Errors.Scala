package br.com.renanpass.flickr.api.error


class FlickrUnknownError(message:String) extends FlickrError(message)

class FlickrKnownError(message:String) extends FlickrError(message)


