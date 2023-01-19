package com.example.newsfresh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfresh.databinding.ItemNewsBinding

//class NewsListAdapter(private val items: ArrayList<String>, private val listener: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
//        val viewHolder = NewsViewHolder(view)
//        view.setOnClickListener{
//            listener.onItemClicked(items[viewHolder.adapterPosition])
//        }
//        return viewHolder
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        val currentItem = items[position]
//        holder.titleView.text = currentItem
//    }
//
//}
//
//class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val titleView: TextView = itemView.findViewById(R.id.title)
//}

interface NewsItemClicked {
    fun onItemClicked(item: String)
}

class NewsListAdapter(private val items: ArrayList<Triple<String, String, String?>>, private val listener: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = NewsViewHolder(view)
        view.imageView.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition].first)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.first
        holder.priceView.text = currentItem.second
        holder.shippingView.text = currentItem.third
        holder.image.setImageResource(R.drawable.color_rectangle)
    }

}

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
    val titleView = binding.title
    val priceView = binding.price
    val shippingView = binding.shipping
    val image = binding.imageView
}
