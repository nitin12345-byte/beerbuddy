package com.hp.beerbuddy.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hp.beerbuddy.R
import com.hp.beerbuddy.databinding.FragmentBeerListingBinding
import com.hp.beerbuddy.model.Beer
import com.hp.beerbuddy.model.networking.NetworkResult
import com.hp.beerbuddy.model.repositories.BeerRepository
import com.hp.beerbuddy.view.adapter.Adapter
import com.hp.beerbuddy.viewmodel.BeerViewModel
import com.hp.beerbuddy.viewmodel.BeerViewModelProviderFactory


class BeerListingFragment : Fragment(), Adapter.ItemClickListener {

    private lateinit var binding: FragmentBeerListingBinding
    private lateinit var viewModel: BeerViewModel
    private var beerList = mutableListOf<Beer>()
    private lateinit var beerAdapter: Adapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProvider = BeerViewModelProviderFactory(BeerRepository(requireContext()))
        viewModel =
            ViewModelProvider(requireActivity(), viewModelProvider)[BeerViewModel::class.java]
        viewModel.getBeerLiveData().observe(this) {

            when (it) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    beerList.clear()
                    beerList.addAll(it.data!!)
                    beerAdapter.notifyDataSetChanged()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
             AlertDialog.Builder(requireContext())
            .setMessage("Something went wrong please try after sometime")
            .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
            .create().show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerListingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beerAdapter = Adapter(beerList)
        beerAdapter.setItemClickListener(this)

        binding.rvProduct.apply {
            this.adapter = beerAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }

        if (beerList.isEmpty()) viewModel.getBeersList()
    }

    override fun onItemClick(beer: Beer) {
        Log.d("ListingFragment", beer.name)
        viewModel.setSelectedBeer(beer)
        findNavController().navigate(R.id.action_beerListingFragment_to_beerDetailsFragment)
    }
}