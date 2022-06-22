package com.product.managefund.Model

import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

data class ResponseGraphModel(
    var code : Int,
    var message : String,
    var error : String,
    var data : HashMap<String, GraphModel>
)

data class GraphModel(
    var data : ArrayList<GraphDetailModel>
)

data class GraphDetailModel(
    var date : String,
    var value : Double,
    var growth : Double
)