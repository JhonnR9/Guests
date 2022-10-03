package me.jhones.guests.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import me.jhones.guests.models.GuestModel
import me.jhones.guests.databinding.FragmentGuestFormBinding
import me.jhones.guests.viewmodels.GuestsFormViewModel


class GuestsFormFragment : Fragment() {
    private var _binding : FragmentGuestFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuestFormBinding.inflate(inflater, container, false)

        val guestsFormViewModel = ViewModelProvider(this)[GuestsFormViewModel::class.java]

        binding.buttonSave.setOnClickListener{
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked
            val guest = GuestModel (0, name,presence)
            guestsFormViewModel.insert(guest)
        }
        binding.radioPresent.isChecked = true

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}