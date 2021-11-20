package com.example.contacts.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.contacts.R

fun createAndShowDialog(
    context: Context,
    message: Int,
    onPositiveAction: () -> Unit,
    onNegativeAction: () -> Unit = {}
) {
    val dialog = AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton(R.string.yes) { dialog, _ ->
            onPositiveAction()
            dialog.dismiss()
        }
        .setNegativeButton(R.string.no) { dialog, _ ->
            onNegativeAction()
            dialog.dismiss()
        }
        .create()

    dialog.show()
}

fun useGlide(
    context: Context,
    image: String,
    view: ImageView
) {
    Glide.with(context)
        .load(image)
        .circleCrop()
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(R.drawable.ic_baseline_person_24)
        .error(R.drawable.ic_baseline_error_24)
        .into(view)
}