package com.example.ecommerceapp

data class CartItem(val product: Product, var quantity: Int)

object Cart {
    private val items = mutableListOf<CartItem>()

    fun addItem(product: Product) {
        val existingItem = items.find { it.product == product }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            items.add(CartItem(product, 1))
        }
    }

    fun getItems(): List<CartItem> = items

    fun getTotalPrice(): Double = items.sumByDouble { it.product.price * it.quantity }

    fun clear() {
        items.clear()
    }
}
