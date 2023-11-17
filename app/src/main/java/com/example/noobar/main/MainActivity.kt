package com.example.noobar.main

import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.noobar.*
import com.example.noobar.adapter.FavoriteAdapter
import com.example.noobar.adapter.MainRecyclerViewAdapter
import com.example.noobar.adapter.RatedAdapter
import com.example.noobar.adapter.TvAdapter
import com.example.noobar.api.popular.PopularApiConfig
import com.example.noobar.api.rated.RatedApiConfig
import com.example.noobar.api.tv.TvApiConfig
import com.example.noobar.response.ResponseFavorite
import com.example.noobar.response.ResponseRated
import com.example.noobar.response.ResponseTV
import com.example.noobar.response.ResultsItemF
import com.example.noobar.response.ResultsItemR
import com.example.noobar.response.ResultsItemT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var imageSlider: ImageSlider
    lateinit var imageList:ArrayList<SlideModel>
    private lateinit var mainRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //variabel recylerView
        val mainItemLayout = layoutInflater.inflate(R.layout.main_item_layout, null)
        val favorite = mainItemLayout.findViewById<RecyclerView>(R.id.rv_favorite)
        val tv = mainItemLayout.findViewById<RecyclerView>(R.id.rv_tv)
        val rated = mainItemLayout.findViewById<RecyclerView>(R.id.rv_rated)

        // Inisialisasi rv_main
        mainRecyclerView = findViewById(R.id.rv_main) // Replace with your actual ID

        // Set layoutManager and adapter for mainRecyclerView
        val favoriteList: List<ResultsItemF?> = emptyList() // Initialize with actual data
        val ratedList: List<ResultsItemR?> = emptyList() // Initialize with actual data
        val tvList: List<ResultsItemT?> = emptyList() // Initialize with actual data

        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = MainRecyclerViewAdapter(this, favoriteList, ratedList, tvList)


        //panggil data favorite
        PopularApiConfig.getService().getFavorite().enqueue(object : Callback<ResponseFavorite> {
            override fun onResponse(
                call: Call<ResponseFavorite>,
                response: Response<ResponseFavorite>
            ) {
                if (response.isSuccessful) {
                    // Inisialisasi adapter dan tampilkan data ke RecyclerView
                    val responseFavorite = response.body()
                    val dataFavorite = responseFavorite?.results
                    val favoriteAdapter = FavoriteAdapter(dataFavorite)
                    favorite.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        setHasFixedSize(true)
                        adapter = favoriteAdapter
                    }
                }
            }


            override fun onFailure(call: Call<ResponseFavorite>, t: Throwable) {
                Toast.makeText(applicationContext,t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })

        //panggil data tv
        TvApiConfig.getService().getTV().enqueue(object : Callback<ResponseTV> {
            override fun onResponse(
                call: Call<ResponseTV>,
                response: Response<ResponseTV>
            ) {
                if (response.isSuccessful) {
                    // Inisialisasi adapter dan tampilkan data ke RecyclerView
                    val responseTV = response.body()
                    val dataTV = responseTV?.results
                    val tvAdapter = TvAdapter(dataTV)
                    tv.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                        setHasFixedSize(true)
                        adapter = tvAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTV>, t: Throwable) {
                Toast.makeText(applicationContext,t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        })

        //panggil data rated
        RatedApiConfig.getService().getRated().enqueue(object : Callback<ResponseRated> {
            override fun onResponse(
                call: Call<ResponseRated>,
                response: Response<ResponseRated>
            ) {
                if (response.isSuccessful) {
                    val responseRated = response.body()
                    val dataRated = responseRated?.results

                    val ratedAdapter = RatedAdapter(dataRated)
                    rated.apply {
                        layoutManager = GridLayoutManager(this@MainActivity, 2) // 2 kolom
                        setHasFixedSize(true)
                        adapter = ratedAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseRated>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })


        imageList = ArrayList()
        imageSlider = findViewById(R.id.image_slider)
        imageList.add(SlideModel("https://e1.pxfuel.com/desktop-wallpaper/776/144/desktop-wallpaper-inside-out-disney-animation-humor-funny-comedy-family-1inside-movie-poster-comedy-movie.jpg","Inside Out"))
        imageList.add(SlideModel("https://nintendosoup.com/wp-content/uploads/2020/01/Sonic-Movie-Poster-Speed-2.jpg","Sonic The Hedgehog"))
        imageList.add(SlideModel("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEh1tiYJq0ESQiYmic7xwcadSz7k4BGMl8AiAbZIFcH0xV7J7euCkfs1_ZG9zx3eCGe3ZYb2P2VpDlJ8qWtDsVw9nImjz5ByqDN8bxfeBkLZAwEHHWMYL9ECuCpkp6-zxU82v0EkBkt7kMrC0RebCLv5gkDJXZ4kvPlcLX3ge8T6E3IWYXha5cHK0LijLw/w1200-h630-p-k-no-nu/resident%20evil%20death%20island.png","Resident Evil: Death Island"))
        imageList.add(SlideModel("https://images.squarespace-cdn.com/content/v1/599219bda803bbef91081e09/1528389737949-M1W198MESICNKQC7QXZC/p_incredible_hero_incredibles2_efeab844.jpeg","Incredibles 2"))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)
    }
}