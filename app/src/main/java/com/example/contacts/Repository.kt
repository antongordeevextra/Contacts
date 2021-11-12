package com.example.contacts

class Repository {

    companion object {
        var listOfContacts = listOf<Contact>(
            Contact("Anton", "Gordeev", "9817868437"),
            Contact("Ekaterina", "Olegovich", "9817868422"),
            Contact("Svetlana", "Popovich", "981786822")
        )
    }
}