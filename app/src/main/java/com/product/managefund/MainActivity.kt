package com.product.managefund

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.product.managefund.View.CompareActivity
import com.product.managefund.View.ListProductActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        // Create Loading Splashscreen
        Handler(Looper.getMainLooper()).postDelayed({
            val main = Intent(this, ListProductActivity::class.java)
            startActivity(main)
        }, 2000)
    }
}