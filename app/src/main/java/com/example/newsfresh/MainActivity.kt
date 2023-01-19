package com.example.newsfresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfresh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

//       get items
        val items = fetchData()

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

        binding.navButton1.performClick()
    }

    private fun fetchData(): ArrayList<Triple<String, String, String?>> {
        val list = ArrayList<String>(100)
        for (i in 1..100) {
            list.add("Item: $i")
        }

        val arr = ArrayList<Triple<String, String, String?>>()
        arr.add(Triple("Item 1", "₹100", "Same day shipping"))
        arr.add(Triple("Item 2", "₹100", null))
        arr.add(Triple("Item 3", "₹100", "Same day shipping"))
        arr.add(Triple("Item 4", "₹100", "Same day shipping"))
        arr.add(Triple("Item 5", "₹100", "Same day shipping"))
        arr.add(Triple("Item 6", "₹100", null))
        arr.add(Triple("Item 7", "₹100", "Same day shipping"))
        arr.add(Triple("Item 8", "₹100", null))
        arr.add(Triple("Item 8", "₹100", null))
        arr.add(Triple("Item 8", "₹100", null))
        arr.add(Triple("Item 8", "₹100", null))
        arr.add(Triple("Item 8", "₹100", null))
        arr.add(Triple("Item 8", "₹100", null))
        arr.add(Triple("Item 8", "₹100", null))

        return arr
    }

    override fun onItemClicked(item: String) {
        Toast.makeText(this, "Clicked item is $item", Toast.LENGTH_LONG).show()
    }

}