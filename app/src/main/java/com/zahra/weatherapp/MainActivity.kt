package com.zahra.weatherapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val CITY : String = "Jakarta, ID"
    val API : String = "de2770d9b7c34d428e9100243213110"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherTask().execute()

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener{
            val intent = Intent(this,ForecastActivity::class.java)
            startActivity(intent)
        }

//        val listView = findViewById<ListView>(R.id.listView)
//        val names = arrayOf("Tomorrow", "Next 2 days", "Next 3 days")
//
//        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//            this, android.R.layout.simple_list_item_1, names
//        )
//
//        listView.adapter = arrayAdapter

//        listView.setOnItemClickListener{ adapterView, view, i, l ->
//            Toast.makeText(this, "Forecast "+ names[i],Toast.LENGTH_LONG)
//                .show()
//        }

    }


    public final inner class weatherTask() : AsyncTask<String, Void, String>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
        }
//
//        override fun listView {
//            val values = arrayListOf("Item One", "Item Two", "Item Three")
//            val mListView = findViewById<ListView>(R.id.listView)
//            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
//            mListView.adapter = adapter
//            mListView.setOnItemClickListener(adapterView, view, i, 1 ->)
//        }

        override fun doInBackground(vararg p0: String?): String? {
            var response:String?
            try {
                response = URL("http://api.weatherapi.com/v1/forecast.json?key=de2770d9b7c34d428e9100243213110&q=Jakarta&days=1&aqi=yes&alerts=yes")
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
                val day = forecast.getJSONObject("day")
                val astro = forecast.getJSONObject("astro")
                val windSpeed = current.getString("wind_kph")
                val weatherDescription = condition.getString("text")
                val updatedAtText = "Updated at "+current.getString("last_updated")
                val temp = current.getString("temp_c")+"°C"
                val tempMin = "Min Temp: "+day.getString("mintemp_c")+"°C"
                val tempMax = "Max Temp: "+day.getString("maxtemp_c")+"°C"
                val pressure = current.getString("pressure_mb")
                val humidity = current.getString("humidity")
                val sunrise = astro.getString("sunrise")
                val sunset = astro.getString("sunset")
                val cloud = current.getString("cloud")
                val precipitation = current.getString("precip_mm")
                val gust = current.getString("gust_kph")
                val address = location.getString("name")+", "+location.getString("country")

                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.precipitation).text = precipitation
                findViewById<TextView>(R.id.gust).text = gust
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity
                findViewById<TextView>(R.id.cloud).text = cloud

                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
                findViewById<TextView>(R.id.errorText).visibility = View.GONE
            }
            catch (e: Exception)
            {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }
        }
    }
}