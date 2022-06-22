package com.product.managefund.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.product.managefund.Model.ProductModel
import com.product.managefund.R
import kotlinx.android.synthetic.main.item_product_list.view.*

class ProductAdapter (var context : Context, var listOfProduct:ArrayList<ProductModel>): RecyclerView.Adapter<ProductAdapter.viewHolderProductList>() {

    var listOfProductSelected : ArrayList<ProductModel> = arrayListOf()

    fun setDefault(){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderProductList {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_product_list, parent,false)
        return viewHolderProductList(view)
    }

    override fun getItemCount() =  listOfProduct.size

    override fun onBindViewHolder(holder: viewHolderProductList, position: Int) {
        var item = listOfProduct[position]

        holder.view.textview_name.text = item.details.im_name
        Glide.with(context).load(item.details.im_avatar).circleCrop().into(holder.view.image_view_product)

        holder.view.cardview_container.setOnClickListener {
            var exist = listOfProductSelected?.filter { x -> x.code.equals(item.code) }
            if(exist.size > 0){
                listOfProductSelected?.remove(item)
                holder.view.cardview_container.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            }else{
                listOfProductSelected?.add(item)
                holder.view.cardview_container.setCardBackgroundColor(Color.parseColor("#82C341"))
            }
        }
    }

    class viewHolderProductList(var view: View): RecyclerView.ViewHolder(view)
}