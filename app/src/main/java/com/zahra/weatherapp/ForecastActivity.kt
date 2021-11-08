package com.zahra.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.json.JSONObject
import java.net.URL

class ForecastActivity : AppCompatActivity() {

    val CITY : String = "Jakarta, ID"
    val API : String = "de2770d9b7c34d428e9100243213110"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        weatherForecast().execute()

//        val actionBar = supportActionBar
//        actionBar!!.title = "Forecast"
//        actionBar.setDisplayHomeAsUpEnabled(true)
//
//        var listview = findViewById<ListView>(R.id.listView)
//        var list = mutableListOf<Model>()
//        listview.adapter = Adapter(this, R.layout.item_list, list)
//
//        list.add(Model("Day 1", "Average Temp", "5.4", "Condition", "Patchy rain"))
//        list.add(Model("Day 2", "Average Temp", "5.4", "Condition", "Patchy rain"))
//        list.add(Model("Day 3", "Average Temp", "5.4", "Condition", "Patchy rain"))
//
//        listview.setOnItemClickListener { parent: AdapterView<*>, view:View, position:Int, id:Long ->
//            val intent = Intent(this,DetailActivity::class.java)
//            startActivity(intent)
//        }

        val btn = findViewById<Button>(R.id.day1Button)

        btn.setOnClickListener{
            val intent = Intent(this,DetailActivity::class.java)
            startActivity(intent)
        }
    }

    inner class weatherForecast() : AsyncTask<String, Void, String>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loaderFor).visibility = View.GONE
            findViewById<RelativeLayout>(R.id.mainForContainer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.errorForText).visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response:String?
            try {
                response = URL("http://api.weatherapi.com/v1/forecast.json?key=de2770d9b7c34d428e9100243213110&q=Jakarta&days=4&aqi=yes&alerts=yes")
                    .readText(Charsets.UTF_8)
            }
            catch (e: Exception)
            {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val current = jsonObj.getJSONObject("current")
                val condition = current.getJSONObject("condition")
                val location = jsonObj.getJSONObject("location")
                val forecast = jsonObj.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                val forecastTwo = jsonObj.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(1)
                val forecastThree = jsonObj.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(2)
                val day = forecast.getJSONObject("day")
                val dayTwo = forecastTwo.getJSONObject("day")
                val dayThree = forecastThree.getJSONObject("day")
                val conditionOne = day.getJSONObject("condition").getString("text")
                val conditionTwo = dayTwo.getJSONObject("condition").getString("text")
                val conditionThree = dayThree.getJSONObject("condition").getString("text")
                val avgTemp = day.getString("avgtemp_c")
                val avgTempTwo = dayTwo.getString("avgtemp_c")
                val avgTempThree = dayThree.getString("avgtemp_c")
                val dateOne = forecast.getString("date")
                val dateTwo = forecastTwo.getString("date")
                val dateThree = forecastThree.getString("date")
                val updatedAtText = "Updated at "+current.getString("last_updated")

                findViewById<TextView>(R.id.updatedat).text = updatedAtText
                findViewById<TextView>(R.id.day1text).text = dateOne
                findViewById<TextView>(R.id.day2text).text = dateTwo
                findViewById<TextView>(R.id.day3text).text = dateThree
                findViewById<TextView>(R.id.avgTemp1).text = avgTemp
                findViewById<TextView>(R.id.avgTemp2).text = avgTempTwo
                findViewById<TextView>(R.id.avgTemp3).text = avgTempThree
                findViewById<TextView>(R.id.condition1).text = conditionOne
                findViewById<TextView>(R.id.condition2).text = conditionTwo
                findViewById<TextView>(R.id.condition3).text = conditionThree

                findViewById<ProgressBar>(R.id.loaderFor).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainForContainer).visibility = View.VISIBLE
                findViewById<TextView>(R.id.errorForText).visibility = View.GONE
            }
            catch (e: Exception)
            {
                findViewById<ProgressBar>(R.id.loaderFor).visibility = View.GONE
                findViewById<TextView>(R.id.errorForText).visibility = View.VISIBLE
            }
        }
    }
}