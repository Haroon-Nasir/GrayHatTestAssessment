package com.example.grayhattest.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.grayhattest.R
import com.example.grayhattest.databinding.ActivityDetailsScreenBinding
import com.example.grayhattest.models.ProductModel
import com.squareup.picasso.Picasso

class DetailsScreen : AppCompatActivity() {
    lateinit var binding: ActivityDetailsScreenBinding
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val product = intent.getSerializableExtra("Product") as ProductModel

        Picasso.get().load(product.product_Image).placeholder(R.drawable.placeholder).into(binding.productImage)

        binding.productName.text = product.product_name

        binding.productType.text = product.product_type

        binding.productPrice.text = product.product_price

        binding.productDetails.text = product.product_details


        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.chalkPink.setOnClickListener {

            binding.chalkPink.background = resources.getDrawable(R.drawable.selected_color_bg, null)

            binding.lightPink.background = resources.getDrawable(R.drawable.unselected_color_bg, null)

            binding.darkOrder.background = resources.getDrawable(R.drawable.unselected_color_bg, null)
        }

        binding.lightPink.setOnClickListener {

            binding.lightPink.background = resources.getDrawable(R.drawable.selected_color_bg, null)

            binding.chalkPink.background = resources.getDrawable(R.drawable.unselected_color_bg, null)

            binding.darkOrder.background = resources.getDrawable(R.drawable.unselected_color_bg, null)
        }

        binding.darkOrder.setOnClickListener {

            binding.darkOrder.background = resources.getDrawable(R.drawable.selected_color_bg, null)

            binding.chalkPink.background = resources.getDrawable(R.drawable.unselected_color_bg, null)

            binding.lightPink.background = resources.getDrawable(R.drawable.unselected_color_bg, null)
        }

        binding.addToCart.setOnClickListener {

            Toast.makeText(this,"product is succesfully added to card",Toast.LENGTH_LONG).show()
        }
    }
}