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