package com.hp.beerbuddy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hp.beerbuddy.databinding.FragmentBeerDetailsBinding
import com.hp.beerbuddy.viewmodel.BeerViewModel

class BeerDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBeerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[BeerViewModel::class.java]
        binding.beer = viewModel.getSelectedBeer()
    }
}