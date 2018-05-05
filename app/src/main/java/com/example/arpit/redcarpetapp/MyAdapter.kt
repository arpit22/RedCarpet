package com.example.arpit.redcarpetapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by Arpit on 05/02/2018.
 */

class MyAdapter(private val items: ArrayList<Countries>, internal var context: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val c = items[i]

        viewHolder.name.text = c.country
        viewHolder.pop.text = "Population: "+c.population
        viewHolder.rank.text = c.rank.toString()

        Picasso.with(context)
                .load(c.flag)
                .into(viewHolder.flag)

        viewHolder.flag.setOnClickListener {
            val i = Intent(context, FullSizePreview::class.java)
            i.putExtra("url_string", c.flag)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var flag: ImageView
        var rank: TextView
        var pop: TextView
        var name: TextView



        init {

            pop = itemView.findViewById<View>(R.id.tPopulation) as TextView
            rank = itemView.findViewById<View>(R.id.tRank) as TextView


            flag = itemView.findViewById<View>(R.id.iFlag) as ImageView
            name = itemView.findViewById<View>(R.id.tName) as TextView


        }
    }


}
