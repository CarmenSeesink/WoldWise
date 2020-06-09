package com.example.worldwise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.worldwise.databinding.FragmentGameWonBinding
import com.example.worldwise.databinding.FragmentTitleBinding


/**
 * A simple [Fragment] subclass.
 */

class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)
        binding.againbutton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_gameWonFragment_to_homeFragment)
        )
        return binding.root
        return inflater.inflate(R.layout.fragment_game_won, container, false)
    }

}
