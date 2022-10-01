package me.jhones.guests.viewmodels

import androidx.lifecycle.ViewModel
import me.jhones.guests.repository.GuestRepository

class GuestsFormViewModel: ViewModel() {
    private val repository = GuestRepository.getInstance()
}