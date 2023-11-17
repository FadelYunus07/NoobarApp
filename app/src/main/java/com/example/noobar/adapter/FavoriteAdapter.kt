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
import com.example.noobar.response.ResultsItemF

class FavoriteAdapter(val dataFavorite: List<ResultsItemF?>?) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgFavorite = view.findViewById<ImageView>(R.id.hero_img)
        val nameMovie = view.findViewById<TextView>(R.id.txt_title)
        val releaseDate = view.findViewById<TextView>(R.id.sub_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_movie_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(dataFavorite!= null){
            return dataFavorite.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (dataFavorite != null && position < dataFavorite.size) {
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"
            holder.nameMovie.text = dataFavorite[position]?.title
            holder.releaseDate.text = dataFavorite[position]?.releaseDate
            Glide.with(holder.imgFavorite)
                .load(IMAGE_BASE + dataFavorite[position]?.posterPath)
                .error(R.drawable.blue)
                .into(holder.imgFavorite)

            holder.itemView.setOnClickListener {
                Log.d("RecyclerView", "Film clicked at position $position")
                val intent = Intent(holder.itemView.context, DetailMovie::class.java)
                intent.putExtra("film_title", dataFavorite[position]?.title)
                intent.putExtra("film_poster", IMAGE_BASE + dataFavorite[position]?.posterPath)
                intent.putExtra("synopsis", dataFavorite[position]?.overview)
                holder.itemView.context.startActivity(intent)
            }
        }
    }


}