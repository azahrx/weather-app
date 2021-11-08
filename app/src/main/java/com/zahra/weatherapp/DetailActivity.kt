package com.zahra.weatherapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import org.json.JSONObject
import java.net.URL

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        weatherDetails().execute()
    }

    inner class weatherDetails() : AsyncTask<String, Void, String>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loaderDet).visibility = View.GONE
            findViewById<RelativeLayout>(R.id.detContainer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.errorDetText).visibility = View.VISIBLE
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
                val minTempOne = day.getString("mintemp_c")
                val minTempTwo = dayTwo.getString("mintemp_c")
                val minTempThree = dayThree.getString("mintemp_c")
                val maxTempOne = day.getString("maxtemp_c")
                val maxTempTwo = dayTwo.getString("maxtemp_c")
                val maxTempThree = dayThree.getString("maxtemp_c")
                val maxWindOne = day.getString("maxwind_kph")
                val maxWindTwo = dayTwo.getString("maxwind_kph")
                val maxWindThree = dayThree.getString("maxwind_kph")
                val totalOne = day.getString("totalprecip_mm")
                val totalTwo = dayTwo.getString("totalprecip_mm")
                val totalThree = dayThree.getString("totalprecip_mm")
                val updatedAtText = "Updated at "+current.getString("last_updated")

                findViewById<TextView>(R.id.updatedatdet).text = updatedAtText
                findViewById<TextView>(R.id.dayone).text = dateOne
                findViewById<TextView>(R.id.daytwo).text = dateTwo
                findViewById<TextView>(R.id.daythree).text = dateThree
                findViewById<TextView>(R.id.temp_min1).text = minTempOne
                findViewById<TextView>(R.id.temp_min2).text = minTempTwo
                findViewById<TextView>(R.id.temp_min3).text = minTempThree
                findViewById<TextView>(R.id.temp_max1).text = maxTempOne
                findViewById<TextView>(R.id.temp_max2).text = maxTempTwo
                findViewById<TextView>(R.id.temp_max3).text = maxTempThree
                findViewById<TextView>(R.id.averTemp1).text = avgTemp
                findViewById<TextView>(R.id.averTemp2).text = avgTempTwo
                findViewById<TextView>(R.id.averTemp3).text = avgTempThree
                findViewById<TextView>(R.id.maxwind1).text = maxWindOne
                findViewById<TextView>(R.id.maxwind2).text = maxWindTwo
                findViewById<TextView>(R.id.maxwind3).text = maxWindThree
                findViewById<TextView>(R.id.totalprecip1).text = totalOne
                findViewById<TextView>(R.id.totalprecip2).text = totalTwo
                findViewById<TextView>(R.id.totalprecip3).text = totalThree
                findViewById<TextView>(R.id.dayweather1).text = conditionOne
                findViewById<TextView>(R.id.dayweather2).text = conditionTwo
                findViewById<TextView>(R.id.dayweather3).text = conditionThree

                findViewById<ProgressBar>(R.id.loaderDet).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.detContainer).visibility = View.VISIBLE
                findViewById<TextView>(R.id.errorDetText).visibility = View.GONE
            }
            catch (e: Exception)
            {
                findViewById<ProgressBar>(R.id.loaderDet).visibility = View.GONE
                findViewById<TextView>(R.id.errorDetText).visibility = View.VISIBLE
            }
        }
    }
}