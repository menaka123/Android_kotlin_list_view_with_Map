package com.example.menakafernando.carsnav

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.GsonBuilder


class Main2Activity : AppCompatActivity() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fileJson:String = applicationContext.assets.open("locations.json").bufferedReader().use {
            it.readText()
        }

        val gson = GsonBuilder().create()

        val feed = gson.fromJson(fileJson, JFeed::class.java)

        val one = feed.placemarks[0]


        var latVal1 = intent.getStringExtra("val1")
        var latVal2 = intent.getStringExtra("val2")
        var vinNumber = intent.getStringExtra("vin")


        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback{
            googleMap = it



            feed.placemarks.forEach{

                if(it.vin == vinNumber) {

                    (googleMap.addMarker(MarkerOptions().position(LatLng(it.coordinates[0].toDouble() , it.coordinates[0].toDouble())).title(it.name))).showInfoWindow()
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latVal1.toDouble(), latVal2.toDouble()), 19f))
                } else {

                    googleMap.addMarker(MarkerOptions().position(LatLng(it.coordinates[0].toDouble() , it.coordinates[0].toDouble())).title(it.name))

                    if(it.vin == one.vin){

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(one.coordinates[0].toDouble(), one.coordinates[0].toDouble()), 10f))
                    }

                }






            }





        })





    }

}
