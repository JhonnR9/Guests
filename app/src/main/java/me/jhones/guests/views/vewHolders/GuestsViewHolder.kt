package me.jhones.guests.views.vewHolders

import androidx.recyclerview.widget.RecyclerView
import me.jhones.guests.databinding.RowGuestBinding
import me.jhones.guests.models.GuestModel
import me.jhones.guests.views.listeners.OnGuestListener

class GuestsViewHolder(private val rowGuestBinding: RowGuestBinding,private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(rowGuestBinding.root) {
    fun bind(guest: GuestModel) {
        rowGuestBinding.textName.text = guest.name
        rowGuestBinding.textName.setOnClickListener {
            listener.onclick(guest.id)
        }
        rowGuestBinding.buttonRemove.setOnClickListener{
            listener.onDelete(guest.id)

        }
    }
}