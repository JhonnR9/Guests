package me.jhones.guests.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
           Toast.makeText(activity?.applicationContext,"test",Toast.LENGTH_SHORT).show()
        }
        binding.radioPresent.isChecked = true

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}