package com.product.managefund.Model

import java.io.Serializable


data class ResponseProductModel(
    var code : Int,
    var message : String,
    var error : String,
    var data : ArrayList<ProductModel>
)

data class ProductModel(
    var code : String,
    var name : String,
    var details : ProductDetailModel
) : Serializable

data class ProductDetailModel(
    var category : String,
    var category_id : Int,
    var currency : String,
    var custody : String,
    var inception_date : String,
    var im_avatar : String,
    var im_name : String,
    var min_balance : Float,
    var min_redemption : Float,
    var min_subscription: Float,
    var nav : Float,
    var return_cur_year : Float,
    var return_five_year : Float,
    var return_four_year : Float,
    var return_inception_growth : Float,
    var return_one_day : Float,
    var return_one_month : Float,
    var return_one_week : Float,
    var return_one_year : Float,
    var return_six_month : Float,
    var return_three_month : Float,
    var return_three_year : Float,
    var return_two_year : Float,
    var total_unit : Int,
    var type : String,
    var type_id : Int,
    var isSelected : Boolean
) : Serializable