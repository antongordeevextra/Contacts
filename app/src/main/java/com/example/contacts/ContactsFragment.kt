package com.example.contacts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.FragmentContactsBinding
import com.example.contacts.databinding.FragmentDetailContactBinding

class ContactsFragment : Fragment(), ContactListAdapter.ContactListListener {

    private lateinit var adapter: ContactListAdapter

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
        val binding = FragmentContactsBinding.bind(view)

        binding.listRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ContactListAdapter(list, this)
        binding.listRecyclerView.adapter = adapter

        binding.swiperefresh.setOnRefreshListener {
            binding.swiperefresh.isRefreshing = false
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance() =
            ContactsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun listItemClicked(contactId: String) {
        listen().launchSecondFragment(contactId)
    }
}