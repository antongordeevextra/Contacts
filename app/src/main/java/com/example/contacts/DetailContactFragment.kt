package com.example.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.contacts.databinding.FragmentDetailContactBinding

class DetailContactFragment : Fragment(R.layout.fragment_detail_contact) {

    companion object {
        const val EXTRA_CONTACT_ID = "EXTRA CONTACT ID"

        fun newInstance(contactId: String) =
            DetailContactFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_CONTACT_ID, contactId)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailContactBinding.bind(view)

        arguments.let {

            val contactId = arguments?.getString(EXTRA_CONTACT_ID, "0")
            val contact = Repository.listOfContacts.single {
                it.id == contactId
            }

            val number = view.findViewById<TextView>(R.id.detailNumber) as TextView
            val name = view.findViewById<TextView>(R.id.detailName) as TextView
            val lastName = view.findViewById<TextView>(R.id.detailLastName) as TextView
//            binding.apply {
//                detailNumber.text = contact.number
//                detailName.text = contact.firstName
//                detailLastName.text = contact.lastName
//            }

            number.text = contact.number
            name.text = contact.firstName
            lastName.text = contact.lastName
        }
    }

}