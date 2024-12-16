package com.example.ecommerceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CartAdapter(Cart.getItems())

        val totalPriceTextView: TextView = view.findViewById(R.id.totalPriceTextView)
        totalPriceTextView.text = "Total: $${Cart.getTotalPrice()}"

        val checkoutButton: Button = view.findViewById(R.id.checkoutButton)
        checkoutButton.setOnClickListener {
            Toast.makeText(context, "Thanks for shopping with us!", Toast.LENGTH_SHORT).show()
            Cart.clear()
            recyclerView.adapter?.notifyDataSetChanged()
            totalPriceTextView.text = "Total: $0.0"
        }
    }
}
