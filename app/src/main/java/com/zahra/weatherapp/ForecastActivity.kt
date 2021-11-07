package com.zahra.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zahra.weatherapp.MainActivity.weatherTask

class ForecastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

//
//        weatherTask().execute()
    }
}