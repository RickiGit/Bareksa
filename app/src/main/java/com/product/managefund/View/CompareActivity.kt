package com.product.managefund.View

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.product.managefund.Adapter.SectionsPagerAdapter
import com.product.managefund.Model.ProductModel
import com.product.managefund.R
import com.product.managefund.ViewModel.ProductViewModel
import com.product.managefund.databinding.ActivityCompareBinding

class CompareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompareBinding
    lateinit var listOfProduct : ArrayList<ProductModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.setTitle(R.string.activity_compare)
        actionBar?.setDisplayHomeAsUpEnabled(false)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        listOfProduct = intent.getSerializableExtra("content") as ArrayList<ProductModel>
        backActivity()
    }

    fun backActivity(){
        binding.imageViewBack.setOnClickListener {
            onBackPressed()
        }
    }

}