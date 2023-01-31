package com.farhan.movieapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.farhan.movieapp.Models.Tv
import kotlinx.android.synthetic.main.tv_item.view.*

class TvAdapter(
    private val tvs : List<Tv>
) : RecyclerView.Adapter<TvAdapter.TvViewHolder>(){

    class TvViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindTv(tv: Tv){
            itemView.tv_title.text = tv.name
            itemView.tv_release.text = tv.first_release
            itemView.tv_popularity.text = tv.popularity.toString()
            val imageLink = tv?.poster

            itemView.tv_image.load(IMAGE_BASE + imageLink){
                crossfade(true)
                crossfade(1000)
            }

//            Glide.with(itemView).load(IMAGE_BASE + tv.poster).into(itemView.tv_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.tv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = tvs[position]
        holder.bindTv(tvs.get(position))

        holder.itemView.setOnClickListener{
            moveToTvDetail(tv, it)
        }
    }

    private fun moveToTvDetail(tv: Tv, it: View) {
        val detailTvsIntent = Intent(it.context, DetailTvActivity::class.java)
        detailTvsIntent.putExtra(DetailTvActivity.EXTRA_TvS, tv)
        it.context.startActivity(detailTvsIntent)
    }

    override fun getItemCount(): Int = tvs.size

}