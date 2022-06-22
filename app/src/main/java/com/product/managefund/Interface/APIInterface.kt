package com.product.managefund.Interface

import com.product.managefund.Model.ResponseGraphModel
import com.product.managefund.Model.ResponseProductModel
import com.product.managefund.Service.APIService
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Query


interface APIInterface {

    @GET("takehometest/apps/compare/detail?productCodes=KI002MMCDANCAS00&productCodes=TP002EQCEQTCRS00&productCodes=NI002EQCDANSIE00")
    open fun getCompareProduct(): Call<ResponseProductModel>

    @GET("takehometest/apps/compare/chart?productCodes=KI002MMCDANCAS00&productCodes=TP002EQCEQTCRS00&productCodes=NI002EQCDANSIE00")
    open fun getGraphProduct(): Call<ResponseGraphModel>

    object APIAllServices {
        val retrofitService = APIService().retrofit.create(APIInterface::class.java)
    }
}