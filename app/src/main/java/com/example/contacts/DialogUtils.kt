package com.example.contacts

import android.app.AlertDialog
import android.content.Context

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