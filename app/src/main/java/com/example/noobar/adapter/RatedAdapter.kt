package com.example.noobar.adapter

import android.content.Context
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
import com.example.noobar.response.ResultsItemF
import com.example.noobar.response.ResultsItemR

class RatedAdapter(val dataRated: List<ResultsItemR?>?) : RecyclerView.Adapter<RatedAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgRated = view.findViewById<ImageView>(R.id.hero_img)
        val nameMovie = view.findViewById<TextView>(R.id.txt_title)
        val releaseDate = view.findViewById<TextView>(R.id.sub_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movie_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(dataRated!= null){
            return dataRated.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (dataRated != null && position < dataRated.size) {
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"
            holder.nameMovie.text = dataRated[position]?.title
            holder.releaseDate.text = dataRated[position]?.releaseDate
            Glide.with(holder.imgRated)
                .load(IMAGE_BASE + dataRated[position]?.posterPath)
                .error(R.drawable.blue)
                .into(holder.imgRated)

            holder.itemView.setOnClickListener {
                Log.d("RecyclerView", "Film clicked at position $position")
                val intent = Intent(holder.itemView.context, DetailMovie::class.java)
                intent.putExtra("film_title", dataRated[position]?.title)
                intent.putExtra("film_poster", IMAGE_BASE + dataRated[position]?.posterPath)
                intent.putExtra("synopsis", dataRated[position]?.overview)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

}

