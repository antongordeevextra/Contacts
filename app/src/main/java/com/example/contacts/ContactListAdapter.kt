package com.example.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactListAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var contact: Contact

        fun bind(contact: Contact) {
            this.contact = contact
            val number = itemView.findViewById<TextView>(R.id.number) as TextView
            val name = itemView.findViewById<TextView>(R.id.name) as TextView
            val lastName = itemView.findViewById<TextView>(R.id.lastName) as TextView

            number.text = contact.number
            name.text = contact.firstName
            lastName.text = contact.lastName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount() = contacts.size
}