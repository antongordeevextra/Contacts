package com.example.contacts

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.contacts.databinding.ContactListItemBinding

class ContactListAdapter(
    private val clickListener: ContactListListener
) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var contacts: List<Contact>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)

        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(contact.id)
        }

        holder.itemView.setOnLongClickListener {

            createAndShowDialog(holder.itemView.context,
            R.string.delete_question,
            onPositiveAction = {clickListener.deleteContact(contact)})
            return@setOnLongClickListener true
        }
    }

    inner class ViewHolder(private val binding: ContactListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.apply {
                number.text = contact.number
                name.text = contact.firstName
                lastName.text = contact.lastName

                Glide.with(itemView)
                    .load(contact.photo)
                    .circleCrop()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.ic_baseline_person_24)
                    .error(R.drawable.ic_baseline_error_24)
                    .into(image)
            }
        }
    }

    interface ContactListListener {
        fun listItemClicked(contactId: Int)
        fun deleteContact(contact: Contact)
    }

    override fun getItemCount() = contacts.size
}