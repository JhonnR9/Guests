package me.jhones.guests.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.jhones.guests.models.GuestModel
import me.jhones.guests.repository.Filter
import me.jhones.guests.repository.GuestRepository

class AbsentsViewModel (application: Application) : AndroidViewModel(application) {

    private val _absentsGuests = MutableLiveData<List<GuestModel>>()
    val absentsGuests: LiveData<List<GuestModel>> = _absentsGuests

    private val repository = GuestRepository.getInstance(application)

    fun getGuests(){
        _absentsGuests.value = repository.getGuests(Filter.ABSENT)
    }
    fun deleteGuest(id: Int){
        repository.deleteGuest(id)
    }
}