package com.example.grayhattest.models

import java.io.Serializable

data class ProductModel(
    var cat_name : String? = null,
    var product_name : String? = null,
    var product_price : String? = null,
    var product_type : String? = null,
    var product_Image : String? = null,
    var product_details : String? = null,
    var product_colors : String? = null
): Serializable