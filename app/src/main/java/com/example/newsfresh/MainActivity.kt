package com.example.newsfresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsfresh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var binding: ActivityMainBinding
    private var items: ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

//       get items
        fetchData()

        binding.navButton1.setOnClickListener{
            binding.newsList.layoutManager = LinearLayoutManager(this)
            val adapter = NewsListAdapter(items, this)
            binding.newsList.adapter = adapter
            binding.navButton1.setBackgroundResource(R.drawable.green_circular_button)
            binding.navButton2.setBackgroundResource(R.drawable.grey_circular_button)
        }

        binding.navButton2.setOnClickListener{
            binding.newsList.layoutManager = GridLayoutManager(this, 3)
            val adapter = NewsGridAdapter(items, this)
            binding.newsList.adapter = adapter
            binding.navButton2.setBackgroundResource(R.drawable.green_circular_button)
            binding.navButton1.setBackgroundResource(R.drawable.grey_circular_button)
        }
    }

    private fun fetchData() {

        val url = "https://run.mocky.io/v3/b6a30bb0-140f-4966-8608-1dc35fa1fadc"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val jsonObject = it.getJSONObject("data")
                val jsonArray = jsonObject.getJSONArray("items")
                for(i in 0 until jsonArray.length()){
                    val itemJsonObject = jsonArray.getJSONObject(i)
                    val item = Item(
                        itemJsonObject.getString("name"),
                        itemJsonObject.getString("price"),
                        itemJsonObject.getString("extra")
                    )
                    items.add(item)
                    binding.loader.visibility = View.GONE
                    binding.navButton1.performClick()
                }
            },
            {
                Log.d("failure", it.localizedMessage)
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: Item) {
        Toast.makeText(this, "Clicked item is ${item.title}", Toast.LENGTH_LONG).show()
    }

}