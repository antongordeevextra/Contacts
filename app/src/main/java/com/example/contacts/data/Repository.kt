package com.example.contacts.data

import com.example.contacts.model.Contact
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

        fun deleteContact(contact: Contact){
            val indexOfContact = listOfContacts.indexOfFirst { it.id == contact.id }
            if(indexOfContact != - 1) {
                listOfContacts = ArrayList(listOfContacts)
                listOfContacts.removeAt(indexOfContact)
            }
        }
    }
}