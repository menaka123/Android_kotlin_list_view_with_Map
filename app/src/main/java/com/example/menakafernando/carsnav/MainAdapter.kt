package com.example.menakafernando.carsnav

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.car_row.view.*

class MainAdapter(val jFeed: JFeed): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {

        return jFeed.placemarks.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0?.context)
        val cellForRow = layoutInflater.inflate(R.layout.car_row, p0, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        val row = jFeed.placemarks[p1]
        p0?.view?.textView_carTitle.text = row.name
        p0?.view.textView_vin.text = row.vin
        p0?.view.textView_address.text = row.address



        p0.data = row
    }
}



class CustomViewHolder(val view: View, var data: Car? = null): RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, Main2Activity::class.java)

            intent.putExtra("val1", data!!.coordinates[0].toString())
            intent.putExtra("val2", data!!.coordinates[0].toString())
            intent.putExtra("vin", data!!.vin)
            view.context.startActivity(intent)
        }
    }
}