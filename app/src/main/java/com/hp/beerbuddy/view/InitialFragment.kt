package com.hp.beerbuddy.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hp.beerbuddy.R
import com.hp.beerbuddy.constant.Constants
import com.hp.beerbuddy.databinding.FragmentInitialBinding

class InitialFragment : Fragment() {

    private lateinit var binding: FragmentInitialBinding
    private lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences =
            requireContext().getSharedPreferences("beerbuddy", Context.MODE_PRIVATE)

        val isFirstTimeVisit =
            sharedPreferences.getBoolean(Constants.KEY_IS_FIRST_TIME_VISIT, false)
        if (isFirstTimeVisit) {
            findNavController().navigate(R.id.action_initialFragment_to_beerListingFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInitialBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener() {
            sharedPreferences.edit().putBoolean(Constants.KEY_IS_FIRST_TIME_VISIT, true).apply()
            findNavController().navigate(R.id.action_initialFragment_to_beerListingFragment)
        }
    }
}