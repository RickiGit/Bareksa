package com.product.managefund.Helper

import com.product.managefund.Entities.Constants
import com.product.managefund.Model.ProductDetailModel
import java.text.SimpleDateFormat
import java.util.*

class Utility {
    fun truncateNumber(floatNumber: Float): String? {
        val thousand = 1000L
        val million = 1000000L
        val billion = 1000000000L
        val trillion = 1000000000000L
        val number = Math.round(floatNumber).toLong()

        if(number >= thousand && number < million){
            val fraction = calculateFraction(number, thousand)
            return String.format("%.0f", fraction) + " Ribu"
        }else if (number >= million && number < billion) {
            val fraction = calculateFraction(number, million)
            return String.format("%.0f", fraction) + " Juta"
        } else if (number >= billion && number < trillion) {
            val fraction = calculateFraction(number, billion)
            return String.format("%.1f", fraction) + " Miliar"
        }else if (number >= trillion){
            val fraction = calculateFraction(number, trillion)
            return String.format("%.1f", fraction) + " Triliun"
        }
        return String.format("%.0f", 0)
    }

    fun calculateFraction(number: Long, divisor: Long): Float {
        val truncate = (number * 10L + divisor / 2L) / divisor
        return truncate.toFloat() * 0.10f
    }

    fun getRisk(value : String) : String{
        if(value.equals(Constants().SAHAM_TYPE)){
            return "Tinggi"
        }

        return "Rendah"
    }

    fun getPeriode(value : String) : String{
        if(value.equals(Constants().SAHAM_TYPE)){
            return "5 Tahun"
        }

        return "1 Tahun"
    }

    fun changeFormatDate(value : String) : String{
        var format = SimpleDateFormat("yyyy-MM-dd")
        var showFormat = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))

        return showFormat.format(format.parse(value))
    }

    fun changeFormatDateMonth(value : String) : String{
        var format = SimpleDateFormat("yyyy-MM-dd")
        var showFormat = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))

        return showFormat.format(format.parse(value))
    }

    fun changeFormatMonth(value : String) : String{
        var format = SimpleDateFormat("yyyy-MM-dd")
        var showFormat = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))

        return showFormat.format(format.parse(value))
    }

    fun changeFormatFull(value : String) : String{
        var format = SimpleDateFormat("yyyy-MM-dd")
        var showFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale("id", "ID"))

        return showFormat.format(format.parse(value))
    }
}