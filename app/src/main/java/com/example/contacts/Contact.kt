package com.example.contacts

import java.io.Serializable


data class Contact (
    var firstName : String,
    var lastName : String,
    var number : String,
    val id : String,
) : Serializable {
    override fun toString(): String {
        return firstName
    }
}