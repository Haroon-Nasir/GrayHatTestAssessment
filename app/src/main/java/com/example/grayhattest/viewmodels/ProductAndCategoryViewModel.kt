package com.example.grayhattest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.grayhattest.connection.FirebaseRepository
import com.example.grayhattest.models.ProductModel


class ProductAndCategoryViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun getProducts(catName : String): LiveData<List<ProductModel>> {
        return repository.getProducts(catName)
    }
    fun getCategories(): LiveData<List<String>> {
        return repository.getCategories()
    }


}