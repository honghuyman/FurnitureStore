package vn.edu.hcmut.furniturestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vn.edu.hcmut.furniturestore.model.Category
import vn.edu.hcmut.furniturestore.repository.CategoryRepository

class CategoryViewModel : ViewModel() {
    private val repository = CategoryRepository()

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchCategories() {
        repository.getCategories(
            onResult = { _categories.value = it },
            onError = { _error.value = it.message }
        )
    }
}

