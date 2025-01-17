package com.example.worldwise

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.worldwise.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), OnCategoryItemClickListener {

    private val viewModel: GameViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.categoryList.adapter = CategoryAdapter(viewModel.categories, this)
        binding.categoryList.layoutManager = LinearLayoutManager(context)
        binding.categoryList.setHasFixedSize(true)
        return binding.root
    }

    override fun onCategoryClick(category: CategoryItem, position: Int, view: View) {
        viewModel.setupGame(category.id)
        view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
    }

}
