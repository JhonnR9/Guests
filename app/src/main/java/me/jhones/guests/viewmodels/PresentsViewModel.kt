package me.jhones.guests.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.jhones.guests.models.GuestModel
import me.jhones.guests.repository.Filter
import me.jhones.guests.repository.GuestRepository

class PresentsViewModel(application: Application) : AndroidViewModel(application) {

    private val _presentsGuests = MutableLiveData<List<GuestModel>>()
    val presentsGuests: LiveData<List<GuestModel>> = _presentsGuests

    private val repository = GuestRepository.getInstance(application)

    fun getGuests(){
        _presentsGuests.value = repository.getGuests(Filter.PRESENT)
    }
    fun deleteGuest(id: Int){
        repository.deleteGuest(id)
    }
}