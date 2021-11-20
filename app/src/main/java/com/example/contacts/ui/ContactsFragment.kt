package com.example.contacts.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import com.example.contacts.R
import com.example.contacts.data.Repository
import com.example.contacts.databinding.FragmentContactsBinding
import com.example.contacts.listen
import com.example.contacts.model.Contact

class ContactsFragment : Fragment(R.layout.fragment_contacts), ContactListAdapter.ContactListListener {

    private lateinit var binding: FragmentContactsBinding
    private val adapter = ContactListAdapter(this)
    private val list = Repository.listOfContacts

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactsBinding.bind(view)

        setupRV()

        setHasOptionsMenu(true)
    }

    private fun setupRV() {
        binding.listRecyclerView.adapter = adapter
        adapter.contacts = list

        resources.getDimensionPixelSize(R.dimen.card_margin).let {
            SpacingItemDecoration(it).let { spacing ->
                binding.listRecyclerView.addItemDecoration(spacing)
            }
        }
    }

    companion object {
        fun newInstance() =
            ContactsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun listItemClicked(contactId: Int) {
        listen().launchSecondFragment(contactId)
    }

    override fun deleteContact(contact: Contact) {
        Repository.deleteContact(contact)
        adapter.contacts = Repository.listOfContacts
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                filterList(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String) {
        val searchQuery = query.lowercase()

        if (searchQuery.isNotEmpty())  {
            adapter.contacts = list.filter {
                it.toString().lowercase().contains(searchQuery)
            }
        } else {
            adapter.contacts = list
        }
    }
}