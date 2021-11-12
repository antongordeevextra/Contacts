package com.example.contacts

class Repository {

    companion object {
        var listOfContacts = arrayListOf<Contact>(
            Contact("Anton", "Gordeev", "9817868437", "0"),
            Contact("Ekaterina", "Olegovich", "9817868422", "1"),
            Contact("Svetlana", "Popovich", "981786822", "2")
        )
    }
}