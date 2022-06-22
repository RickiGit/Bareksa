package com.product.managefund.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.product.managefund.Interface.APIInterface
import com.product.managefund.Model.ResponseGraphModel
import com.product.managefund.Model.ResponseProductModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {
    private val _responseProduct = MutableLiveData<ResponseProductModel>()
    val responseProduct : LiveData<ResponseProductModel>
        get() = _responseProduct

    private val _responseGraph = MutableLiveData<ResponseGraphModel>()
    val responseGraph : LiveData<ResponseGraphModel>
        get() = _responseGraph


    fun getProductCompare(){
        GlobalScope.launch {
            try{
                val res: Call<ResponseProductModel> = APIInterface.APIAllServices.retrofitService.getCompareProduct()
                res.enqueue(object : Callback<ResponseProductModel?> {
                    override fun onResponse(call: Call<ResponseProductModel?>?, Response: Response<ResponseProductModel?>?) {
                        _responseProduct.value = Response?.body()
                    }

                    override fun onFailure(call: Call<ResponseProductModel?>?, t: Throwable) {
                        Log.d("Error API", t.message.toString())
                    }
                })
            }
            catch(t : Throwable){
                Log.d("Error API", t.message.toString())
            }
        }
    }

    fun getGraphCompare(){
        GlobalScope.launch {
            try{
                val res: Call<ResponseGraphModel> = APIInterface.APIAllServices.retrofitService.getGraphProduct()
                res.enqueue(object : Callback<ResponseGraphModel?> {
                    override fun onResponse(call: Call<ResponseGraphModel?>?, Response: Response<ResponseGraphModel?>?) {
                        _responseGraph.value = Response?.body()
                    }

                    override fun onFailure(call: Call<ResponseGraphModel?>?, t: Throwable) {
                        Log.d("Error API", t.message.toString())
                    }
                })
            }
            catch(t : Throwable){
                Log.d("Error API", t.message.toString())
            }
        }
    }
}