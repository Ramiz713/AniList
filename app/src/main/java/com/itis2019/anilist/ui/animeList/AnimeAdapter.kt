package com.itis2019.anilist.ui.animeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itis2019.anilist.R
import com.itis2019.anilist.entitites.anime.AnimeItem
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
        fun bind(item: AnimeItem?) {
            item?.let {
                with(it) {
                    val context = containerView.context
                    tv_anime_name.text = title
                    tv_anime_rank.text = context.getString(R.string.rank, rank)
                    tv_anime_score.text = score.toString()
                    tv_anime_type.text = context.getString(R.string.type_and_episodes, type, episodes)
                    Glide.with(containerView)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder)
                        .into(image_anime)
                }
            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<AnimeItem>() {

            override fun areItemsTheSame(oldItem: AnimeItem, newItem: AnimeItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: AnimeItem, newItem: AnimeItem) =
                oldItem == newItem
        }
    }
}
