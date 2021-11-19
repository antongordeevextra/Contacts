package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : AppCompatActivity(), Abstraction {

    private var contactsFragment = ContactsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, contactsFragment)
                .commit()
        }
    }

    override fun launchSecondFragment(contactId: Int) {
        if (findViewById<FrameLayout>(R.id.fragment_container_2) != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_2, DetailContactFragment.newInstance(contactId))
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailContactFragment.newInstance(contactId))
                .addToBackStack(null)
                .commit()
        }
    }
}
