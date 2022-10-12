package me.jhones.guests.views.listeners

abstract class OnGuestListener {
   open fun onclick(id: Int) {}
   open fun onDelete(id: Int) {}
}