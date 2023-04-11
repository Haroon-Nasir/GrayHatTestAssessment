package com.example.grayhattest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.grayhattest.adapters.ProductAdapter
import com.example.grayhattest.databinding.ActivityHomeScreenBinding
import com.example.grayhattest.viewmodels.ProductAndCategoryViewModel
import com.google.android.material.tabs.TabLayout
import com.google.firebase.FirebaseApp

class HomeScreen : AppCompatActivity() {

    lateinit var binding: ActivityHomeScreenBinding

    private lateinit var viewModel: ProductAndCategoryViewModel

    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ProductAndCategoryViewModel::class.java)

        viewModel.getCategories().observe(this) { categoryList ->

            for (item in categoryList) {
                binding.tabLayout.addTab(binding.tabLayout.newTab().setText(item))
            }

        }

        binding.searchProduct.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                // This method is called after the text has been changed.
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                    productAdapter.filter.filter(binding.searchProduct.text.toString())


            }
        })



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                viewModel.getProducts(tab.text.toString()).observe(this@HomeScreen) { productsList ->

                    binding.productsRV.layoutManager = GridLayoutManager(this@HomeScreen,2)

                    productAdapter = ProductAdapter(this@HomeScreen,productsList)

                    binding.productsRV.adapter = productAdapter
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })



    }


}