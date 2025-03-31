//package vn.edu.hcmut.furniturestore
//
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import android.view.Menu
//import android.view.MenuItem
//import android.view.WindowManager
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import vn.edu.hcmut.furniturestore.adapter.CategoryAdapter
//import vn.edu.hcmut.furniturestore.viewmodel.CategoryViewModel
//
//class CategoryActivity : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var categoryViewModel: CategoryViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//
//
//        setContentView(R.layout.activity_category)
//
//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
//
//        categoryViewModel.categories.observe(this) { categories ->
//            recyclerView.adapter = CategoryAdapter(categories)
//        }
//
//        categoryViewModel.error.observe(this) { errorMessage ->
//            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
//        }
//
//        categoryViewModel.fetchCategories()
//    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.nav_user -> {
//                val intent = Intent(this, ProfileActivity::class.java)
//                startActivity(intent)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//}
package vn.edu.hcmut.furniturestore

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import vn.edu.hcmut.furniturestore.adapter.CategoryAdapter
import vn.edu.hcmut.furniturestore.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContentView(R.layout.activity_category)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize ViewModel
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        // Setup category data
        categoryViewModel.categories.observe(this) { categories ->
            recyclerView.adapter = CategoryAdapter(categories)
        }

        categoryViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        // Setup bottom navigation
        bottomNavView = findViewById(R.id.bottom_nav_view)
        setupBottomNavigation()

        // Fetch categories
        categoryViewModel.fetchCategories()
    }

    private fun setupBottomNavigation() {
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_user -> {
                    // This is the part that wasn't working
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    // Keep these methods for action bar menu if needed
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_user -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}