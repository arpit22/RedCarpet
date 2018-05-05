package com.example.arpit.redcarpetapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private var recyView: RecyclerView? = null

    private var mydata: ArrayList<Countries>? = null
    private var myadapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        recyView = findViewById<View>(R.id.rView) as RecyclerView
        recyView!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyView!!.layoutManager = layoutManager
        loadJSON()


    }


    private fun loadJSON() {

        val retrofit = Retrofit.Builder()
                .baseUrl(RetrofitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create<RetrofitApi>(RetrofitApi::class.java)

        val call = api.jsonResponse


        //expensive method
        call.enqueue(object : Callback<JSONResponse> {
            override fun onResponse(call: Call<JSONResponse>, response: Response<JSONResponse>) {
                val jsonResponse = response.body()

                mydata = ArrayList(Arrays.asList(*jsonResponse!!.countries))
                Observable.just(mydata!!)
                myadapter = MyAdapter(mydata!!, applicationContext)
                recyView!!.adapter = myadapter
            }

            override fun onFailure(call: Call<JSONResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })

    }


}
