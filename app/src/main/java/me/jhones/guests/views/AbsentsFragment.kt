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
import me.jhones.guests.databinding.FragmentAbsentsBinding
import me.jhones.guests.databinding.FragmentPresentsBinding
import me.jhones.guests.viewmodels.AbsentsViewModel
import me.jhones.guests.viewmodels.PresentsViewModel
import me.jhones.guests.views.adapters.GuestsAdapter
import me.jhones.guests.views.listeners.OnGuestListener

class AbsentsFragment : Fragment() {

    private val guestsAdapter = GuestsAdapter()

    private var _binding: FragmentAbsentsBinding? = null
    private var _absentsViewModel: AbsentsViewModel? = null

    private val binding get() = _binding!!
    private val absentsViewModel get() = _absentsViewModel!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _absentsViewModel = ViewModelProvider(this).get(AbsentsViewModel::class.java)

        _binding = FragmentAbsentsBinding.inflate(inflater, container, false)

        binding.recyclerAbsentsGuests.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = guestsAdapter
        }

        absentsViewModel.getGuests()

        val listener = object : OnGuestListener() {


            override fun onclick(id: Int) {
                Toast.makeText(context, "Test on Click", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Int) {
                absentsViewModel.deleteGuest(id)
                absentsViewModel.getGuests()
            }

        }

        guestsAdapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun observe() {
        absentsViewModel.absentsGuests.observe(viewLifecycleOwner) {
            guestsAdapter.updateGuest(it)
        }
    }
}