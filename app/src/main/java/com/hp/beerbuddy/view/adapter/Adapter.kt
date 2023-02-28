package com.hp.beerbuddy.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hp.beerbuddy.databinding.BeerListItemBinding
import com.hp.beerbuddy.model.Beer

class Adapter(private val beers: List<Beer>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var itemClickListener: ItemClickListener? = null

    interface ItemClickListener {
        fun onItemClick(beer: Beer)
    }

    class ViewHolder(val binding: BeerListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BeerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.beer = beers[position]
        holder.binding.root.setOnClickListener {
            itemClickListener?.onItemClick(beers[position])
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return beers.size
    }
}