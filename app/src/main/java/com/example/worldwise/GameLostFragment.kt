package com.example.worldwise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.worldwise.databinding.FragmentGameLostBinding

/**
 * A simple [Fragment] subclass.
 */
class GameLostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameLostBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_lost, container, false)
        binding.trybutton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_gameLostFragment_to_homeFragment)
        )
        return binding.root
    }

}
