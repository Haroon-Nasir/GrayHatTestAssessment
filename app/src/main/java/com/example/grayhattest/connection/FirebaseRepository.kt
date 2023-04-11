package com.example.grayhattest.connection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.grayhattest.models.ProductModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseRepository {

    private val database = FirebaseDatabase.getInstance()

    private val productsRef = database.getReference("products")

    private val categoryRef = database.getReference("categories")

    fun getCategories(): LiveData<List<String>> {

        val data = MutableLiveData<List<String>>()

        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val categoryList = dataSnapshot.value as ArrayList<String>

                data.value = categoryList
            }

            override fun onCancelled(databaseError: DatabaseError) {

               Log.d("error",databaseError.message.toString())

            }
        })

        return data
    }

    fun getProducts(catName : String): LiveData<List<ProductModel>> {

        val data = MutableLiveData<List<ProductModel>>()

        productsRef.orderByChild("cat_name").equalTo(catName).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val productsList = ArrayList<ProductModel>()

                for (productSnapshot in dataSnapshot.children) {
                    val product = productSnapshot.getValue(ProductModel::class.java)
                    if (product != null) {
                        productsList.add(product)
                    }
                }

                data.value = productsList
            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.d("error",databaseError.message.toString())

            }
        })

        return data
    }



}
