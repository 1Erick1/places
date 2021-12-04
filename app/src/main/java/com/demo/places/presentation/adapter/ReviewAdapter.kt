package com.demo.places.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.places.databinding.ItemReviewBinding
import com.demo.places.presentation.model.ReviewModel
import com.demo.places.presentation.util.loadCircle

class ReviewAdapter(): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    private val list = mutableListOf<ReviewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemReviewBinding.inflate(
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

    fun setItems(items: List<ReviewModel>){
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root){

        fun bindView(review: ReviewModel){
            with(binding){
                review.profilePicUrl?.let { ivProfile.loadCircle(it) }
                tvName.text = review.authorName
                tvReview.text = review.text
                tvTimeAgo.text = review.timeAgoDescription
            }
        }
    }

}