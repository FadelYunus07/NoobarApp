package com.example.noobar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noobar.R
import com.example.noobar.response.ResultsItemF
import com.example.noobar.response.ResultsItemR
import com.example.noobar.response.ResultsItemT

class MainRecyclerViewAdapter(
    private val context: Context,
    private val favoriteListItems: List<ResultsItemF?>,
    private val ratedListItems: List<ResultsItemR?>,
    private val tvListItems: List<ResultsItemT?>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_FAVORITE = 0
        private const val TYPE_RATED = 1
        private const val TYPE_TV = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item_layout, parent, false)
        return when (viewType) {
            TYPE_FAVORITE -> FavoriteViewHolder(view)
            TYPE_RATED -> RatedViewHolder(view)
            else -> TVViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoriteViewHolder -> {
                if (!favoriteListItems.isNullOrEmpty()) {
                    holder.bind(favoriteListItems)
                }
            }
            is RatedViewHolder -> {
                if (!ratedListItems.isNullOrEmpty()) {
                    holder.bind(ratedListItems)
                }
            }
            is TVViewHolder -> {
                if (!tvListItems.isNullOrEmpty()) {
                    holder.bind(tvListItems)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when {
            position < favoriteListItems.size -> TYPE_FAVORITE
            position < favoriteListItems.size + ratedListItems.size -> TYPE_RATED
            else -> TYPE_TV
        }
    }

    override fun getItemCount(): Int {
        return favoriteListItems.size + ratedListItems.size + tvListItems.size
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_favorite)

        fun bind(favoriteList: List<ResultsItemF?>) {
            val adapter = FavoriteAdapter(favoriteList)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    inner class RatedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_rated)

        fun bind(ratedList: List<ResultsItemR?>) {
            val adapter = RatedAdapter(ratedList)
            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    inner class TVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_tv)

        fun bind(tvList: List<ResultsItemT?>) {
            val adapter = TvAdapter(tvList)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}
