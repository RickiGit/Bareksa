package com.product.managefund.View.Fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.product.managefund.Helper.Utility
import com.product.managefund.Model.GraphModel
import com.product.managefund.Model.ProductDetailModel
import com.product.managefund.Model.ProductModel
import com.product.managefund.R
import com.product.managefund.ViewModel.ProductViewModel
import com.product.managefund.databinding.FragmentImbalHasilBinding
import io.github.farshidroohi.ChartEntity
import com.product.managefund.Model.GraphDetailModel
import com.product.managefund.View.CompareActivity


class ImbalHasilFragment : Fragment() {

    private lateinit var binding : FragmentImbalHasilBinding
    private lateinit var viewModelProduct : ProductViewModel

    private lateinit var listOfProduct : ArrayList<ProductModel>
    private lateinit var listOfGraph : HashMap<String, GraphModel>
    private lateinit var listOfGraphOne : ArrayList<GraphDetailModel>
    private lateinit var listOfGraphTwo : ArrayList<GraphDetailModel>
    private lateinit var listOfGraphThree : ArrayList<GraphDetailModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelProduct = ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentImbalHasilBinding.inflate(inflater, container, false)

        setTabsRangePeriode()
        setData()
        //getProductCompare()
        setDataToLayout()

        return binding.root
    }

    fun setChartFilter(filter : Int){

        listOfGraphOne = listOfGraph[listOfProduct[0].code]!!.data
        listOfGraphTwo = listOfGraph[listOfProduct[1].code]!!.data
        if(listOfProduct.size > 2){
            listOfGraphThree = listOfGraph[listOfProduct[2].code]!!.data
        }

        if(filter == 0){

            var firstIndex = 0
            var firstChart : MutableList<Float> = arrayListOf()
            for(i in listOfGraphOne.size downTo listOfGraphOne.size - 5 ){
                firstChart?.add(listOfGraphOne[i-1].growth.toFloat())
                firstIndex++
            }

            firstIndex = 0
            var secondChart : MutableList<Float> = arrayListOf()
            for(j in listOfGraphTwo.size downTo listOfGraphTwo.size - 5){
                secondChart?.add(listOfGraphTwo[j-1].growth.toFloat())
                firstIndex++
            }

            firstIndex = 0
            var thirdChart : MutableList<Float> = arrayListOf()
            if(listOfProduct.size > 2){
                for(j in listOfGraphThree.size downTo listOfGraphThree.size - 5){
                    thirdChart?.add(listOfGraphThree[j-1].growth.toFloat())
                    firstIndex++
                }
            }


            var legendArray : java.util.ArrayList<String> = arrayListOf()
            for(k in listOfGraphTwo.size downTo listOfGraphTwo.size - 5){
                legendArray?.add(Utility().changeFormatDateMonth(listOfGraphTwo[k-1].date))
            }

            var legendSort : java.util.ArrayList<String> = arrayListOf()
            for(k in legendArray.size-1 downTo 0){
                legendSort.add(legendArray[k])
            }

            val firstChartEntity = ChartEntity(Color.parseColor("#668054"), firstChart.toFloatArray())
            val secondChartEntity = ChartEntity(Color.parseColor("#725E9C"), secondChart.toFloatArray())
            val thirdChartEntity = ChartEntity(Color.parseColor("#6D98B6"), thirdChart.toFloatArray())

            if(listOfProduct.size > 2){
                val list = ArrayList<ChartEntity>().apply {
                    add(firstChartEntity)
                    add(secondChartEntity)
                    add(thirdChartEntity)
                }
                binding.textviewPercentThree.text = listOfGraphThree.get(listOfGraphThree.size - 1).growth.toString() + "%"
                binding.lineChartGraph.setLegend(legendSort)
                binding.lineChartGraph.setList(list)
            }else{
                val list = ArrayList<ChartEntity>().apply {
                    add(firstChartEntity)
                    add(secondChartEntity)
                }
                binding.imageViewPercentThree.visibility = View.GONE
                binding.textviewPercentThree.visibility = View.GONE
                binding.lineChartGraph.setLegend(legendSort)
                binding.lineChartGraph.setList(list)
            }

            binding.textviewPercentOne.text = listOfGraphOne.get(listOfGraphOne.size - 1).growth.toString() + "%"
            binding.textviewPercentTwo.text = listOfGraphTwo.get(listOfGraphTwo.size - 1).growth.toString() + "%"
            binding.textviewDate.text = Utility().changeFormatFull(listOfGraphOne.get(listOfGraphOne.size - 1).date)

            binding.progressBarGraph.visibility = View.GONE
        }
    }

    fun setTabsRangePeriode(){
        binding.tabsRangePeriode.addTab(binding.tabsRangePeriode.newTab().setText("1W"))
        binding.tabsRangePeriode.addTab(binding.tabsRangePeriode.newTab().setText("1M"))
        binding.tabsRangePeriode.addTab(binding.tabsRangePeriode.newTab().setText("1Y"))
        binding.tabsRangePeriode.addTab(binding.tabsRangePeriode.newTab().setText("3Y"))
        binding.tabsRangePeriode.addTab(binding.tabsRangePeriode.newTab().setText("5Y"))
        binding.tabsRangePeriode.addTab(binding.tabsRangePeriode.newTab().setText("10Y"))
        binding.tabsRangePeriode.addTab(binding.tabsRangePeriode.newTab().setText("All"))
    }

    fun filterData(){
        binding.tabsRangePeriode.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    fun getGraphCompare(){
        viewModelProduct.getGraphCompare()
    }

    fun setData(){
        var page = activity as CompareActivity
        this.listOfProduct = page.listOfProduct

        if(listOfProduct.size > 0){
            setProductOne(listOfProduct[0].details)
        }

        if(listOfProduct.size > 1){
            setProductTwo(listOfProduct[1].details)
        }

        if(listOfProduct.size > 2){
            setProductThree(listOfProduct[2].details)
        }else{
            hiddenProduct()
        }

        getGraphCompare()
    }

    fun setDataToLayout(){
        viewModelProduct.responseGraph?.observe(this.viewLifecycleOwner, Observer { it ->
            it.let {
                if(it.message.equals("Success")) {
                    listOfGraph = it.data
                    setChartFilter(0)
                }
            }
        })
    }

    fun setProductOne(productDetailModel: ProductDetailModel){
        binding.textviewNameOne.text = productDetailModel.im_name
        Glide.with(this).load(productDetailModel.im_avatar).circleCrop().into(binding.imageViewItemOne)
        binding.textviewTypeOne.text = productDetailModel.type
        binding.textviewResultOne.text = String.format("%.2f%%", productDetailModel.return_cur_year)
        binding.textviewMinBuyOne.text = Utility().truncateNumber(productDetailModel.min_subscription)
        binding.textviewManageFundOne.text = Utility().truncateNumber(productDetailModel.total_unit * productDetailModel.nav)
        binding.textviewTimePeriodeOne.text = Utility().getPeriode(productDetailModel.type)
        binding.textviewRiskOne.text = Utility().getRisk(productDetailModel.type)
        binding.textviewReleaseOne.text = Utility().changeFormatDate(productDetailModel.inception_date)
    }

    fun setProductTwo(productDetailModel: ProductDetailModel){
        binding.textviewNameTwo.text = productDetailModel.im_name
        Glide.with(this).load(productDetailModel.im_avatar).circleCrop().into(binding.imageViewItemTwo)
        binding.textviewTypeTwo.text = productDetailModel.type
        binding.textviewResultTwo.text = String.format("%.2f%%", productDetailModel.return_cur_year)
        binding.textviewMinBuyTwo.text = Utility().truncateNumber(productDetailModel.min_subscription)
        binding.textviewManageFundTwo.text = Utility().truncateNumber(productDetailModel.total_unit * productDetailModel.nav)
        binding.textviewTimePeriodeTwo.text = Utility().getPeriode(productDetailModel.type)
        binding.textviewRiskTwo.text = Utility().getRisk(productDetailModel.type)
        binding.textviewReleaseTwo.text = Utility().changeFormatDate(productDetailModel.inception_date)
    }

    fun setProductThree(productDetailModel: ProductDetailModel){
        binding.textviewNameThree.text = productDetailModel.im_name
        Glide.with(this).load(productDetailModel.im_avatar).circleCrop().into(binding.imageViewItemThree)
        binding.textviewTypeThree.text = productDetailModel.type
        binding.textviewResultThree.text = String.format("%.2f%%", productDetailModel.return_cur_year)
        binding.textviewMinBuyThree.text = Utility().truncateNumber(productDetailModel.min_subscription)
        binding.textviewManageFundThree.text = Utility().truncateNumber(productDetailModel.total_unit * productDetailModel.nav)
        binding.textviewTimePeriodeThree.text = Utility().getPeriode(productDetailModel.type)
        binding.textviewRiskThree.text = Utility().getRisk(productDetailModel.type)
        binding.textviewReleaseThree.text = Utility().changeFormatDate(productDetailModel.inception_date)
    }

    fun hiddenProduct(){
        binding.containerProductNameThree.visibility = View.GONE
        binding.relativeContainerManageFundThree.visibility = View.GONE
        binding.relativeContainerMinBuyThree.visibility = View.GONE
        binding.relativeContainerReleaseThree.visibility = View.GONE
        binding.relativeContainerResultThree.visibility = View.GONE
        binding.relativeContainerTypeProductThree.visibility = View.GONE
        binding.relativeContainerRiskThree.visibility = View.GONE
        binding.relativeContainerTimePeriodThree.visibility = View.GONE
        binding.relativeContainerDetailThree.visibility = View.GONE
        binding.relativeContainerBuyThree.visibility = View.GONE
    }
}