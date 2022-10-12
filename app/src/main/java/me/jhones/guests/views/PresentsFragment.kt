package me.jhones.guests.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.jhones.guests.databinding.FragmentPresentsBinding
import me.jhones.guests.viewmodels.PresentsViewModel
import me.jhones.guests.views.adapters.GuestsAdapter
import me.jhones.guests.views.listeners.OnGuestListener

class PresentsFragment : Fragment() {
    private val guestsAdapter = GuestsAdapter()

    private var _binding: FragmentPresentsBinding? = null
    private var _presentsViewModel: PresentsViewModel? = null

    private val binding get() = _binding!!
    private val presentsViewModel get() = _presentsViewModel!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _presentsViewModel = ViewModelProvider(this).get(PresentsViewModel::class.java)

        _binding = FragmentPresentsBinding.inflate(inflater, container, false)

        binding.recyclerPresentsGuests.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = guestsAdapter
        }
        val listener = object : OnGuestListener() {

            override fun onclick(id: Int) {
                Toast.makeText(context, "Test on Click", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Int) {
                presentsViewModel.deleteGuest(id)
                presentsViewModel.getGuests()
            }

        }

        guestsAdapter.attachListener(listener)

        presentsViewModel.getGuests()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        presentsViewModel.presentsGuests.observe(viewLifecycleOwner) {
            guestsAdapter.updateGuest(it)
        }
    }
}