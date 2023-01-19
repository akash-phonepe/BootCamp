package com.example.newsfresh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfresh.databinding.CardViewBinding

//class NewsGridAdapter(pri) {
//}

class NewsGridAdapter(private val items: ArrayList<Triple<String, String, String?>>, private val listener: NewsItemClicked) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = CardViewHolder(view)
        view.cardImage.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition].first)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.first
        holder.priceView.text = currentItem.second
        holder.image.setImageResource(R.drawable.color_rectangle)
    }

}

class CardViewHolder(private val binding: CardViewBinding): RecyclerView.ViewHolder(binding.root){
    val titleView = binding.cardTitle
    val priceView = binding.cardPrice
    val image = binding.cardImage
}