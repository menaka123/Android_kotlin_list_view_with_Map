package com.example.menakafernando.carsnav

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.car_list.*


class CarListActivity: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        //val view = inflater!!.inflate(R.layout.car_list, container, false)

        return LayoutInflater.from(container?.context).inflate(R.layout.car_list, container, false)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        val fileJson:String = activity!!.applicationContext.assets.open("locations.json").bufferedReader().use {
            it.readText()
        }

        val gson = GsonBuilder().create()

        val feed = gson.fromJson(fileJson, JFeed::class.java)


        recyclerView_carList.layoutManager = LinearLayoutManager(activity)

        recyclerView_carList.adapter = MainAdapter(feed)


    }



}

class JFeed(val placemarks: List<Car>)

class Car(val name: String, val vin: String, val address: String,
          val engineType: String, val exterior: String, val fuel: Int, val interior: String,
          val coordinates: List<Float>)
