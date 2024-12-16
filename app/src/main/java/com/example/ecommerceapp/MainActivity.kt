package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fragmentContainer = findViewById(R.id.fragment_container)

        val categories = listOf(
            Category("Shirts", R.drawable.shirt_image, 100.0),
            Category("Trousers", R.drawable.trousers_image, 150.0),
            Category("Hoodies", R.drawable.hoodie_image, 150.0),
            Category("Jackets", R.drawable.jacket_image, 250.0),
            Category("Shorts", R.drawable.shorts_image, 100.0),
            Category("Jeans", R.drawable.jeans_image, 200.0),
            Category("Shoes", R.drawable.shoes_image, 250.0),
            Category("Belts", R.drawable.belts_image, 50.0),
            Category("Wallets", R.drawable.wallets_image, 50.0)
        )

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = CategoryAdapter(categories) { category ->
            replaceFragment(ProductListFragment.newInstance(category.name, category.price))
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showRecyclerView()
                    removeCurrentFragment()
                    true
                }
                R.id.nav_cart -> {
                    replaceFragment(CartFragment())
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, UserProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        recyclerView.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showRecyclerView() {
        recyclerView.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
    }

    private fun removeCurrentFragment() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            if (fragmentManager.backStackEntryCount == 1) {
                showRecyclerView()
            }
        } else {
            super.onBackPressed()
        }
    }
}
