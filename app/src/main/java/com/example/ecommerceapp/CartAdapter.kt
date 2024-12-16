package com.example.ecommerceapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItems: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
    }

    override fun getItemCount(): Int = cartItems.size

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.productName)
        private val quantityTextView: TextView = itemView.findViewById(R.id.productQuantity)
        private val priceTextView: TextView = itemView.findViewById(R.id.productPrice)

        fun bind(cartItem: CartItem) {
            nameTextView.text = cartItem.product.name
            quantityTextView.text = "Qty: ${cartItem.quantity}"
            priceTextView.text = "$${cartItem.product.price * cartItem.quantity}"
        }
    }
}
