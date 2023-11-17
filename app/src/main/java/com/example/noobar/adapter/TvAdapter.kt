package com.example.noobar.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.noobar.R
import com.example.noobar.main.DetailMovie
import com.example.noobar.response.ResultsItemT

class TvAdapter(val dataTV: List<ResultsItemT?>?) : RecyclerView.Adapter<TvAdapter.MyViewHolder>() {
    class MyViewHolder (view: View): RecyclerView.ViewHolder(view){
        val imgTV = view.findViewById<ImageView>(R.id.hero_img)
        val nameMovie = view.findViewById<TextView>(R.id.txt_title)
        val releaseDate = view.findViewById<TextView>(R.id.sub_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movie_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(dataTV != null){
            return dataTV.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (dataTV != null && position < dataTV.size) {
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"
            holder.nameMovie.text = dataTV[position]?.name
            holder.releaseDate.text = dataTV[position]?.firstAirDate
            Glide.with(holder.imgTV)
                .load(IMAGE_BASE + dataTV[position]?.posterPath)
                .error(R.drawable.blue)
                .into(holder.imgTV)

            holder.itemView.setOnClickListener {
                Log.d("RecyclerView", "Film clicked at position $position")
                val intent = Intent(holder.itemView.context, DetailMovie::class.java)
                intent.putExtra("film_title", dataTV[position]?.name)
                intent.putExtra("film_poster", IMAGE_BASE + dataTV[position]?.posterPath)
                intent.putExtra("synopsis", dataTV[position]?.overview)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

}