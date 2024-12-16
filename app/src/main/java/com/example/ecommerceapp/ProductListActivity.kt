package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        Log.d("ProductListActivity", "onCreate: ProductListActivity started")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Products"

        val category = intent.getStringExtra("category")
        val price = intent.getDoubleExtra("price", 0.0)

        val products = when (category) {
            "Shirts" -> listOf(Product("Shirt 1", "M", price), Product("Shirt 2", "L", price))
            "Trousers" -> listOf(Product("Trousers 1", "M", price), Product("Trousers 2", "L", price))
            "Hoodies" -> listOf(Product("Hoodie 1", "M", price), Product("Hoodie 2", "L", price))
            "Jackets" -> listOf(Product("Jacket 1", "M", price), Product("Jacket 2", "L", price))
            else -> {
                Log.e("ProductListActivity", "onCreate: Unknown category $category")
                emptyList()
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductAdapter(products)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Close activity and navigate back
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
