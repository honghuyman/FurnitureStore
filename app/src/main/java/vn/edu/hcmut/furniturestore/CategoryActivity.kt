package vn.edu.hcmut.furniturestore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.edu.hcmut.furniturestore.adapter.CategoryAdapter
import vn.edu.hcmut.furniturestore.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        categoryViewModel.categories.observe(this) { categories ->
            recyclerView.adapter = CategoryAdapter(categories)
        }

        categoryViewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        categoryViewModel.fetchCategories()
    }
}
