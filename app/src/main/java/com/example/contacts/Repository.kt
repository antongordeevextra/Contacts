package com.example.contacts

import com.github.javafaker.Faker

class Repository {

    companion object {
        var listOfContacts = arrayListOf<Contact>()

        init {
            val faker = Faker.instance()

            listOfContacts = (1..100).map{
                Contact(
                    id = it,
                    firstName = faker.name().firstName(),
                    lastName = faker.name().lastName(),
                    number = faker.phoneNumber().phoneNumber(),
                    photo = "https://picsum.photos/500"
                )
            } as ArrayList<Contact>
        }
    }
}