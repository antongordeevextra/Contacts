package com.example.contacts

import java.io.Serializable


data class Contact (
    val firstName : String,
    val lastName : String,
    val number : String
) : Serializable {
    override fun toString(): String {
        return firstName
    }
}