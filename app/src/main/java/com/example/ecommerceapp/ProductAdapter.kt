package com.example.ecommerceapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.productName)
        private val sizeTextView: TextView = view.findViewById(R.id.productSize)
        private val priceTextView: TextView = view.findViewById(R.id.productPrice)
        private val addToCartButton: Button = view.findViewById(R.id.addToCartButton)

        fun bind(product: Product) {
            nameTextView.text = product.name
            sizeTextView.text = product.size
            priceTextView.text = "$${product.price}"
            addToCartButton.setOnClickListener {
                Cart.addItem(product)
            }
        }
    }
}
