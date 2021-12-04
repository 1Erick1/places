package com.demo.places.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.places.databinding.ItemFavoriteBinding
import com.demo.places.presentation.model.PlaceDetailModel

class FavoriteAdapter(private val onClick: (PlaceDetailModel)->Unit): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private val list = mutableListOf<PlaceDetailModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(items: List<PlaceDetailModel>){
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                onClick(list[adapterPosition])
            }
        }

        fun bindView(place: PlaceDetailModel){
            with(binding){
                tvName.text = place.name
                tvAddress.text = place.address
            }
        }
    }


}