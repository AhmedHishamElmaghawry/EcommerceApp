package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = arguments?.getString("category")
        val price = arguments?.getDouble("price") ?: 0.0 // Provide a default value if price is null

        val products = when (category) {
            "Shirts" -> listOf(Product("Shirt 1", "M", price), Product("Shirt 2", "L", price))
            "Trousers" -> listOf(Product("Trousers 1", "M", price), Product("Trousers 2", "L", price))
            "Hoodies" -> listOf(Product("Hoodie 1", "M", price), Product("Hoodie 2", "L", price))
            "Jackets" -> listOf(Product("Jacket 1", "M", price), Product("Jacket 2", "L", price))
            "Shorts" -> listOf(Product("Short 1", "M", price), Product("Short 2", "L", price))
            "Jeans" -> listOf(Product("Jean 1", "M", price), Product("Jean 2", "L", price))
            "Shoes" -> listOf(Product("Shoe 1", "M", price), Product("Shoe 2", "L", price))
            "Belts" -> listOf(Product("Belt 1", "M", price), Product("Belt 2", "L", price))
            "Wallets" -> listOf(Product("Wallet 1", "M", price), Product("Wallet 2", "L", price))
            else -> {
                Log.e("ProductListFragment", "Unknown category $category")
                emptyList()
            }
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ProductAdapter(products)
    }

    companion object {
        fun newInstance(category: String, price: Double): ProductListFragment {
            val fragment = ProductListFragment()
            val args = Bundle()
            args.putString("category", category)
            args.putDouble("price", price)
            fragment.arguments = args
            return fragment
        }
    }
}
