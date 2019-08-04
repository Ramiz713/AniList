package com.itis2019.anilist.ui.mangaList

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
import com.itis2019.anilist.entitites.MangaItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.anime_item.*
import kotlinx.android.synthetic.main.anime_item.view.*

class MangaAdapter(private val listener: ((Pair<MangaItem, ImageView>)) -> Unit) :
    PagedListAdapter<MangaItem, MangaAdapter.MangaHolder>(DIFF_CALLBACK_MANGA) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaHolder =
        MangaHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.anime_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MangaHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        val image = holder.itemView.image_anime
        item?.let {
            holder.itemView.setOnClickListener { listener(Pair(item, image)) }
            ViewCompat.setTransitionName(image, item.title)
        }
    }


    class MangaHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: MangaItem?) {
            item?.let {
                with(it) {
                    val context = containerView.context
                    tv_anime_name.text = title
                    tv_anime_rank.text = context.getString(R.string.rank, rank)
                    tv_anime_score.text = score.toString()
                    tv_anime_type.text = context.getString(R.string.type_and_episodes, type, volumes)
                    Glide.with(containerView)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder)
                        .into(image_anime)
                }
            }

        }
    }

    companion object {
        private val DIFF_CALLBACK_MANGA = object :
            DiffUtil.ItemCallback<MangaItem>() {

            override fun areItemsTheSame(oldItem: MangaItem, newItem: MangaItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MangaItem, newItem: MangaItem) =
                oldItem == newItem
        }
    }
}
