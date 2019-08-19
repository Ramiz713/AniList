package com.itis2019.anilist.ui.animeDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.itis2019.anilist.R
import com.itis2019.anilist.databinding.ActivityAnimeDetailsBinding
import com.itis2019.anilist.di.di
import com.itis2019.anilist.entitites.anime.AnimeItem
import com.itis2019.anilist.ui.ViewModelFactory
import com.itis2019.anilist.utils.EXTRA_ANIME_ITEM
import com.itis2019.anilist.utils.EXTRA_IMAGE
import kotlinx.android.synthetic.main.activity_anime_details.*
import org.kodein.di.generic.instance

class AnimeDetailsActivity : AppCompatActivity() {

    private val viewModelFactory: ViewModelFactory by di.instance()

    private lateinit var viewModel: AnimeDetailsViewModel

    private lateinit var binding: ActivityAnimeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AnimeDetailsViewModel::class.java)

        binding = DataBindingUtil
            .setContentView(this@AnimeDetailsActivity, R.layout.activity_anime_details)

        observeError()
        observeLoading()

        with(intent.getParcelableExtra<AnimeItem>(EXTRA_ANIME_ITEM)) {
            Glide.with(this@AnimeDetailsActivity).load(imageUrl).into(image_anime)
            image_anime.transitionName = intent.getStringExtra(EXTRA_IMAGE)
            observeAnimeItem(id)
            binding.animeItem = this
        }
        setSupportActionBar(detailsToolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = "Main Info"
        }
    }

    private fun observeAnimeItem(id: Int) = viewModel.getAnimeById(id).observe(this, Observer {
        binding.animeItem = it
    })

    private fun observeLoading() =
        viewModel.isLoading().observe(this, Observer {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

    private fun observeError() =
        viewModel.error().observe(this, Observer {
            Snackbar.make(details_container, it.localizedMessage, Snackbar.LENGTH_SHORT).show()
        })
}
