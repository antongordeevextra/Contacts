package com.example.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.contacts.databinding.FragmentDetailContactBinding

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

            Glide.with(view.context)
                .load(contact.photo)
                .circleCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_baseline_person_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.imageViewDetail)
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