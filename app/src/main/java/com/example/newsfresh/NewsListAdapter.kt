package com.example.newsfresh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfresh.databinding.ItemNewsBinding


interface NewsItemClicked {
    fun onItemClicked(item: Item)
}

class NewsListAdapter(private val items: ArrayList<Item>, private val listener: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = NewsViewHolder(view)
        view.imageView.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.priceView.text = currentItem.price
        holder.shippingView.text = if(currentItem.extra == "null") " " else currentItem.extra
        holder.image.setImageResource(R.drawable.color_rectangle)
    }

    fun updateItem(updateItems: ArrayList<Item>){
        items.clear()
        items.addAll(updateItems)

        notifyDataSetChanged()
    }

}

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
    val titleView = binding.title
    val priceView = binding.price
    val shippingView = binding.shipping
    val image = binding.imageView
}
