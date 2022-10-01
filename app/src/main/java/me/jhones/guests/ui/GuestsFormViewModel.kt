package me.jhones.guests.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuestsFormViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is guest form  Fragment"
    }
    val text: LiveData<String> = _text
}