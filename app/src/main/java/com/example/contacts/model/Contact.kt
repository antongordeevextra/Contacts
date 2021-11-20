package com.example.contacts.model


data class Contact (
    var firstName : String,
    var lastName : String,
    var number : String,
    val id : Int,
    val photo: String
)