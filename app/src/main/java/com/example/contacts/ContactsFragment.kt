package com.example.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = Repository.listOfContacts

        recyclerView = view.findViewById(R.id.listRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ContactListAdapter(list)

    }

    companion object {
        fun newInstance() =
            ContactsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}