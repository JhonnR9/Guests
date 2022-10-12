package me.jhones.guests.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.jhones.guests.databinding.FragmentAllGuestsBinding
import me.jhones.guests.viewmodels.AllGuestsViewModel
import me.jhones.guests.views.adapters.GuestsAdapter
import me.jhones.guests.views.listeners.OnGuestListener


class AllGuestsFragment : Fragment() {
    private val guestsAdapter = GuestsAdapter()

    private var _binding: FragmentAllGuestsBinding? = null
    private var _allGuestsViewModel: AllGuestsViewModel? = null

    private val binding get() = _binding!!
    private val allGuestsViewModel get() = _allGuestsViewModel!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _allGuestsViewModel = ViewModelProvider(this)[AllGuestsViewModel::class.java]

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        binding.recyclerAllGuests.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = guestsAdapter
        }

        val listener = object : OnGuestListener(){

            override fun onclick(id: Int) {
                Toast.makeText(context, "Test on Click", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Int) {
                allGuestsViewModel.deleteGuest(id)
                allGuestsViewModel.getGuests()
            }

        }

        guestsAdapter.attachListener(listener)

       allGuestsViewModel.getGuests()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _allGuestsViewModel = null
    }

    private fun observe() {
        allGuestsViewModel.allGuests.observe(viewLifecycleOwner) {
            guestsAdapter.updateGuest(it)
        }
    }
}