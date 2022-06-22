package com.product.managefund

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.product.managefund.View.CompareActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create Loading Splashscreen
        Handler(Looper.getMainLooper()).postDelayed({
            val main = Intent(this, CompareActivity::class.java)
            startActivity(main)
        }, 2000)
    }
}