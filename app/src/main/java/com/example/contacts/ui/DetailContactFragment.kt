package com.example.contacts.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.contacts.R
import com.example.contacts.data.Repository
import com.example.contacts.databinding.FragmentDetailContactBinding
import com.example.contacts.model.Contact
import com.example.contacts.utils.useGlide

class DetailContactFragment : Fragment(R.layout.fragment_detail_contact) {

    private lateinit var contact: Contact

    companion object {
        const val EXTRA_CONTACT_ID = "EXTRA CONTACT ID"

        fun newInstance(contactId: Int) =
            DetailContactFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_CONTACT_ID, contactId)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailContactBinding.bind(view)

        arguments.let {
            val contactId = arguments?.getInt(EXTRA_CONTACT_ID, 0)
            contact = Repository.listOfContacts.single {
                it.id == contactId
            }

            val number = view.findViewById<TextView>(R.id.detailNumber) as TextView
            val name = view.findViewById<TextView>(R.id.detailName) as TextView
            val lastName = view.findViewById<TextView>(R.id.detailLastName) as TextView

            number.text = contact.number
            name.text = contact.firstName
            lastName.text = contact.lastName

            useGlide(view.context,
            contact.photo,
            binding.imageViewDetail)
        }

        binding.fab.setOnClickListener {

            contact.firstName = binding.detailName.text.toString()
            contact.lastName = binding.detailLastName.text.toString()
            contact.number = binding.detailNumber.text.toString()

            Toast.makeText(activity, "Changes are saved", Toast.LENGTH_SHORT).show()

            parentFragmentManager.popBackStack()
        }
    }
}