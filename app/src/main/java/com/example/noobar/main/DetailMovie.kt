package com.example.noobar.main

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.noobar.R
import com.example.noobar.adapter.FavoriteAdapter
import com.example.noobar.api.popular.PopularApiConfig
import com.example.noobar.response.ResponseFavorite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        val favorite = findViewById<RecyclerView>(R.id.rv_detail_favorite)

        val filmTitle = intent.getStringExtra("film_title")
        val filmPoster = intent.getStringExtra("film_poster")
        val filmSynopsis = intent.getStringExtra("synopsis")

        val txtTitle: TextView = findViewById(R.id.txt_detail_title)
        val heroImg: ImageView = findViewById(R.id.detail_hero_img)
        val synopsisTextView: TextView = findViewById(R.id.textViewSynopsis)

        txtTitle.text = filmTitle
        Glide.with(this)
            .load(filmPoster)
            .error(R.drawable.ic_launcher_background)
            .into(heroImg)

        synopsisTextView.text = filmSynopsis


        //panggil data favorite
        PopularApiConfig.getService().getFavorite().enqueue(object : Callback<ResponseFavorite> {
            override fun onResponse(
                call: Call<ResponseFavorite>,
                response: Response<ResponseFavorite>
            ) {
                if (response.isSuccessful){
                    val responseFavorite = response.body()
                    val dataFavorite = responseFavorite?.results
                    val favoriteAdapter = FavoriteAdapter(dataFavorite)
                    //list data favorite movie
                    favorite.apply {
                        layoutManager = LinearLayoutManager(this@DetailMovie,LinearLayoutManager.HORIZONTAL,false)
                        setHasFixedSize(true)
                        favoriteAdapter.notifyDataSetChanged()
                        adapter = favoriteAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseFavorite>, t: Throwable) {
                Toast.makeText(applicationContext,t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }
}