package com.product.managefund.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.product.managefund.Adapter.ProductAdapter
import com.product.managefund.Model.ProductModel
import com.product.managefund.R
import com.product.managefund.ViewModel.ProductViewModel
import com.product.managefund.databinding.ActivityListProductBinding
import kotlinx.android.synthetic.main.activity_list_product.*

class ListProductActivity : AppCompatActivity() {

    private lateinit var viewModelProduct : ProductViewModel
    private lateinit var binding : ActivityListProductBinding
    private lateinit var adapter : ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelProduct = ViewModelProviders.of(this).get(ProductViewModel::class.java)

        getProduct()
        setLayoutData()
        goToCompare()
    }

    fun getProduct(){
        viewModelProduct.getProductCompare()
    }

    fun setLayoutData(){
        viewModelProduct.responseProduct?.observe(this, Observer { it ->
            it.let {
                if(it.message.equals("Success")){
                    adapter = ProductAdapter(this, it.data)
                    var linearLayoutManager = LinearLayoutManager(this)
                    binding.recyclerViewProduct.layoutManager = linearLayoutManager
                    binding.recyclerViewProduct.adapter = adapter

                    binding.progressBarProduct.visibility = View.GONE
                }
            }
        })
    }

    fun goToCompare(){
        button_compare.setOnClickListener {
            var listOfSelected = adapter.listOfProductSelected
            if(listOfSelected.size > 1){
                var intent = Intent(this, CompareActivity::class.java)
                intent.putExtra("content", listOfSelected)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Pilih 2 atau 3 produk", Toast.LENGTH_SHORT).show()
            }
        }
    }
}