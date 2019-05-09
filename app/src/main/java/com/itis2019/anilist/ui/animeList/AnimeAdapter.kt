package com.itis2019.anilist.ui.animeList

import android.arch.paging.PagedListAdapter
import android.support.v4.view.ViewCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.itis2019.anilist.R
import com.itis2019.anilist.entitites.AnimeItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.anime_item.*
import kotlinx.android.synthetic.main.anime_item.view.*

class AnimeAdapter(private val listener: ((Pair<AnimeItem, ImageView>)) -> Unit) :
    PagedListAdapter<AnimeItem, AnimeAdapter.AnimeHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeHolder =
        AnimeHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.anime_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AnimeHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        val image = holder.itemView.image_anime
        item?.let {
            holder.itemView.setOnClickListener { listener(Pair(item, image)) }
            ViewCompat.setTransitionName(image, item.title)
        }
    }


    class AnimeHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: AnimeItem?): Unit {
            tv_anime_name.text = item?.title
            Glide.with(containerView).load(item?.imageUrl).into(image_anime)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<AnimeItem>() {
            override fun areItemsTheSame(
                oldItem: AnimeItem,
                newItem: AnimeItem
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: AnimeItem,
                newItem: AnimeItem
            ) = oldItem == newItem
        }
    }
}
