package com.example.daggersample.networking

data class User(
    var address: Address? = null,
    var company: Company? = null,
    var email: String? = null,
    var id: Int? = null,
    var name: String? = null,
    var phone: String? = null,
    var username: String? = null,
    var website: String? = null
)

data class Company(
    var bs: String? = null,
    var catchPhrase: String? = null,
    var name: String? = null
)

data class Address(
    var city: String? = null,
    var geo: Geo? = null,
    var street: String? = null,
    var suite: String? = null,
    var zipcode: String? = null
)

data class Geo(
    var lat: String? = null,
    var lng: String? = null
)

data class Post(
    var body: String? = null,
    var id: Int? = null,
    var title: String? = null,
    var userId: Int? = null
)

data class  BlogPost(
    var pk:Int?=(1..9999).shuffled().first(),
    var title:String?="title",
    var body:String?=null,
    var image:String?="https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/Home+screen/Codingwithmitch.com+(1).png"
){
    override fun toString(): String {
        return title.toString()
    }
}


data class ErrorResponse(
    var status:Int?=0,
    var msg:String?=""
)