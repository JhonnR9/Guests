package me.jhones.guests.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.jhones.guests.models.GuestModel
import me.jhones.guests.repository.Filter
import me.jhones.guests.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val _allGuests = MutableLiveData<List<GuestModel>>()
    val allGuests: LiveData<List<GuestModel>> = _allGuests

    private val repository = GuestRepository.getInstance(application)

    fun getGuests() {
        _allGuests.value = repository.getGuests(Filter.ALL)
    }
    fun deleteGuest(id: Int){
        repository.deleteGuest(id)
    }
}