package com.example.xmlusingretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.xmlusingretrofit.R
import retrofit2.Retrofit
import com.example.xmlusingretrofit.MainActivity
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import com.example.xmlusingretrofit.FeedAPI
import com.example.xmlusingretrofit.model.Feed
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private  val TAG = "MainActivity"
    private  val BASE_URL = "https://www.reddit.com/r/"

    lateinit var btnFetch:Button
    lateinit var tvTitle:TextView

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: MyAdapter

    private lateinit var titles: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titles = arrayListOf()

        rvMain = findViewById(R.id.rvMain)

        rvAdapter = MyAdapter(titles)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)


        btnFetch = findViewById(R.id.btnFetch)


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        val feedAPI = retrofit.create(FeedAPI::class.java)
        val call = feedAPI.feed

        btnFetch.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                call!!.enqueue(object : Callback<Feed?> {
                    override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                        Log.d(TAG, "onResponse: feed: " + response.body().toString())
                        Log.d(TAG, "onResponse: Server Response: $response")
                        val entries = response.body()!!.entrys
                        for (entry in entries!!) {
                            Log.d(TAG, "onResponse: " + entry.title)
                            titles.add(entry.title.toString())
                            rvMain.adapter?.notifyDataSetChanged()

                        }
                    }

                    override fun onFailure(call: Call<Feed?>, t: Throwable) {
                        Log.e(TAG, "onFailure: Unable to retrieve RSS: " + t.message)
                        Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT).show()
                    }
                })

            }

        })

    }

}
