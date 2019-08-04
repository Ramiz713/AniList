package com.itis2019.anilist.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.itis2019.anilist.R
import com.itis2019.anilist.databinding.ActivityAnimeDetailsBinding
import com.itis2019.anilist.entitites.AnimeItem
import com.itis2019.anilist.utils.EXTRA_ANIME_ITEM
import com.itis2019.anilist.utils.EXTRA_IMAGE
import kotlinx.android.synthetic.main.activity_anime_details.*

class AnimeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAnimeDetailsBinding = DataBindingUtil
            .setContentView(this@AnimeDetailsActivity, R.layout.activity_anime_details)

        with(intent.getParcelableExtra<AnimeItem>(EXTRA_ANIME_ITEM)) {
            Glide.with(this@AnimeDetailsActivity).load(imageUrl).into(image_anime)
            image_anime.transitionName = intent.getStringExtra(EXTRA_IMAGE)
            binding.animeItem = this
        }
    }
}
