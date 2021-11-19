package com.example.contacts

import androidx.fragment.app.Fragment

fun Fragment.listen() : Abstraction = requireActivity() as Abstraction

interface Abstraction {
    fun launchSecondFragment(contactId: Int)
}