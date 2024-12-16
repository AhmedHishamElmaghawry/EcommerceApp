package com.example.ecommerceapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CartAdapter(Cart.getItems())

        val totalPriceTextView: TextView = findViewById(R.id.totalPriceTextView)
        totalPriceTextView.text = "Total: $${Cart.getTotalPrice()}"

        val checkoutButton: Button = findViewById(R.id.checkoutButton)
        checkoutButton.setOnClickListener {
            Toast.makeText(this, "Thanks for shopping with us!", Toast.LENGTH_SHORT).show()
            Cart.clear()
            recyclerView.adapter?.notifyDataSetChanged()
            totalPriceTextView.text = "Total: $0.0"
        }
    }
}
