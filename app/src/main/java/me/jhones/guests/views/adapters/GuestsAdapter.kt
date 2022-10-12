package me.jhones.guests.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.jhones.guests.databinding.RowGuestBinding
import me.jhones.guests.models.GuestModel
import me.jhones.guests.views.listeners.OnGuestListener
import me.jhones.guests.views.vewHolders.GuestsViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestsViewHolder>() {
    private lateinit var guests: List<GuestModel>
    private lateinit var myOnGuestListener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val itemView = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestsViewHolder(itemView, myOnGuestListener)
    }

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guests[position])
    }

    override fun getItemCount(): Int {
        return if (::guests.isInitialized) guests.count() else 0
    }

    fun updateGuest(guests: List<GuestModel>) {
        this.guests = guests
        notifyDataSetChanged()
    }
    fun attachListener(onGuestListener: OnGuestListener){
        myOnGuestListener = onGuestListener
    }

}