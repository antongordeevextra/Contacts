package com.example.contacts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.FragmentContactsBinding
import com.example.contacts.databinding.FragmentDetailContactBinding

class ContactsFragment : Fragment(R.layout.fragment_contacts), ContactListAdapter.ContactListListener {

    private lateinit var binding: FragmentContactsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactsBinding.bind(view)

        setupRV()
    }

    private fun setupRV() {
        val list = Repository.listOfContacts

        val adapter = ContactListAdapter(this)
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
}