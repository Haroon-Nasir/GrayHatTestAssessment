package com.example.grayhattest.adapters


import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grayhattest.ui.DetailsScreen
import com.example.grayhattest.R
import com.example.grayhattest.models.ProductModel
import com.squareup.picasso.Picasso


class ProductAdapter(private val context: Context, private val productList: List<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {

    var productListFilter: List<ProductModel>

    init {
        productListFilter = productList
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.productImage)

        val productName: TextView = view.findViewById(R.id.productName)

        val productType: TextView = view.findViewById(R.id.productType)

        val productPrice: TextView = view.findViewById(R.id.productPrice)

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.products_item_view,
            viewGroup,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        Picasso.get().load(productList[position].product_Image).placeholder(R.drawable.placeholder)
            .into(viewHolder.productImage)

        viewHolder.productName.text = productList[position].product_name

        viewHolder.productType.text = productList[position].product_type

        viewHolder.productPrice.text = productList[position].product_price

        viewHolder.itemView.setOnClickListener {

            context.startActivity(
                Intent(context, DetailsScreen::class.java).putExtra(
                    "Product",
                    productList[position]
                )
            )

        }

    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = productListFilter.size


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charString = constraint?.toString() ?: ""

                val filteredList = if (charString.isEmpty()) {
                    productList
                } else {
                    val resultList = ArrayList<ProductModel>()

                    productList.filter {
                        it.cat_name!!.contains(
                            charString,
                            ignoreCase = true
                        ) || it.product_name!!.contains(charString, ignoreCase = true)
                    }.forEach { resultList.add(it) }

                    resultList
                }

                val filterResults = FilterResults()

                filterResults.values = filteredList

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")

                productListFilter = results?.values as ArrayList<ProductModel>? ?: ArrayList()

                notifyDataSetChanged()
            }
        }

    }


}
