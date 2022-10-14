package me.jhones.guests.views.vewHolders

import android.app.AlertDialog
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import me.jhones.guests.R
import me.jhones.guests.databinding.RowGuestBinding
import me.jhones.guests.models.GuestModel
import me.jhones.guests.views.listeners.OnGuestListener

class GuestsViewHolder(
    private val rowGuestBinding: RowGuestBinding,
    private val listener: OnGuestListener
) :
    RecyclerView.ViewHolder(rowGuestBinding.root) {
    fun bind(guest: GuestModel) {
        rowGuestBinding.textName.text = guest.name
        rowGuestBinding.textName.setOnClickListener {
            listener.onclick(guest.id)
        }
        rowGuestBinding.buttonRemove.setOnClickListener {
            val title: String = it.context.getString(R.string.delete_alert_title)
            val message: String = it.context.getString(R.string.delete_alert_message)
            val yes: String = it.context.getString(R.string.delete_alert_yes)
            val no: String = it.context.getString(R.string.delete_alert_no)
            val successMessage: String = it.context.getString(R.string.delete_alert_success)

            AlertDialog.Builder(it.context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(yes) { _, _ ->
                    Toast.makeText(it.context, successMessage,Toast.LENGTH_SHORT).show()
                    listener.onDelete(guest.id)
                }
                .setNegativeButton(no, null)
                .create()
                .show()


        }
    }
}