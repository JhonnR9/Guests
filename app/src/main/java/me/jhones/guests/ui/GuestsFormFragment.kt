package me.jhones.guests.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import me.jhones.guests.databinding.FragmentGuestFormBinding



class GuestsFormFragment : Fragment() {
    private var _binding : FragmentGuestFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuestFormBinding.inflate(inflater, container, false)

        val guestsFormViewModel = ViewModelProvider(this)[GuestsFormViewModel::class.java]
        val text: TextView = binding.textHome
        guestsFormViewModel.text.observe(viewLifecycleOwner) {
            text.text = it
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}