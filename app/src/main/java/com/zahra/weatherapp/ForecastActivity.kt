package com.zahra.weatherapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.zahra.weatherapp.MainActivity.weatherTask
import org.json.JSONObject
import java.net.URL

class ForecastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

//
//        weatherForecast().execute()
        var listview = findViewById<ListView>(R.id.listView)
        var list = mutableListOf<Model>()
        listview.adapter = Adapter(this, R.layout.item_list, list)

        list.add(Model("Day 1", "Average Temp", "5.4", "Condition", "Patchy rain"))
        list.add(Model("Day 2", "Average Temp", "5.4", "Condition", "Patchy rain"))
        list.add(Model("Day 3", "Average Temp", "5.4", "Condition", "Patchy rain"))

        listview.setOnItemClickListener { parent: AdapterView<*>, view:View, position:Int, id:Long ->
            if (position == 0) {
                Toast.makeText(this, "Test 1", Toast.LENGTH_LONG).show()
            }
            if (position == 1) {
                Toast.makeText(this, "Test 2", Toast.LENGTH_LONG).show()
            }
            if (position == 2) {
                Toast.makeText(this, "Test 3", Toast.LENGTH_LONG).show()
            }
        }
    }

//    inner class weatherForecast() : AsyncTask<String, Void, String>()
//    {
//        override fun onPreExecute() {
//            super.onPreExecute()
//            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
//            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
//            findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
//        }
//
//        override fun doInBackground(vararg p0: String?): String? {
//            var response:String?
//            try {
//                response = URL("http://api.weatherapi.com/v1/forecast.json?key=de2770d9b7c34d428e9100243213110&q=Jakarta&days=1&aqi=yes&alerts=yes")
//                    .readText(Charsets.UTF_8)
//            }
//            catch (e: Exception)
//            {
//                response = null
//            }
//            return response
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            try {
//                val jsonObj = JSONObject(result)
//                val current = jsonObj.getJSONObject("current")
//                val condition = current.getJSONObject("condition")
//                val location = jsonObj.getJSONObject("location")
//                val forecast = jsonObj.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
//                val day = forecast.getJSONObject("day")
//                val conditionForecast = day.getJSONObject("condition")
//                val avgTemp = day.getString("avgtemp_c")
//                val airCond = conditionForecast.getString("text")
//                val address = location.getString("name")+", "+location.getString("country")
//                val updatedAtText = "Updated at "+current.getString("last_updated")
//
//                findViewById<TextView>(R.id.address).text = address
//                findViewById<TextView>(R.id.updated_at).text = updatedAtText
//                findViewById<TextView>(R.id.avgtemp).text = avgTemp
//                findViewById<TextView>(R.id.aircondition).text = airCond
//
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
//                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
//                findViewById<TextView>(R.id.errorText).visibility = View.GONE
//            }
//            catch (e: Exception)
//            {
//                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
//                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
//            }
//        }
//    }
}