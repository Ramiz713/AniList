package com.itis2019.anilist.ui.animeList

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.itis2019.anilist.R
import com.itis2019.anilist.di.di
import com.itis2019.anilist.ui.ViewModelFactory
import com.itis2019.anilist.ui.animeDetails.AnimeDetailsActivity
import com.itis2019.anilist.utils.EXTRA_ANIME_ITEM
import com.itis2019.anilist.utils.EXTRA_IMAGE
import kotlinx.android.synthetic.main.fragment_anime_list.*
import org.kodein.di.generic.instance

class AnimeListFragment : Fragment() {

    private lateinit var imageView: ImageView
    private val viewModelFactory: ViewModelFactory by di.instance()
    private lateinit var viewModel: AnimeListViewModel

    private val adapter = AnimeAdapter { pair ->
        imageView = pair.second
        viewModel.movieClicked(pair)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AnimeListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_anime_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        observeLoading()
        observeError(view)
        observeAnimeList()
        observeItemClick()
    }

    private fun observeLoading() =
        viewModel.isLoading().observe(this, Observer {
            it?.let { isLoading ->
                progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        })

    private fun observeError(view: View) {
        viewModel.isError().observe(this, Observer {
            Snackbar.make(view, it?.localizedMessage ?: "Problems", Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun observeAnimeList() =
        viewModel.onLoadNextPage().observe(this, Observer {
            adapter.submitList(it)
        })

    private fun initRecycler(view: View) {
        val manager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
            2,
            androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
        )
        val recyclerView =
            view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_anime)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    private fun observeItemClick() =
        viewModel.navigateToAnimeDetails.observe(this, Observer {
            it?.let { pair ->
                val intent = Intent(activity, AnimeDetailsActivity::class.java)
                intent.putExtra(EXTRA_ANIME_ITEM, pair.first)
                val transitionName = ViewCompat.getTransitionName(imageView) ?: ""
                intent.putExtra(EXTRA_IMAGE, transitionName)
                val transitionActivityOptions =
                    ActivityOptions.makeSceneTransitionAnimation(activity, pair.second, transitionName)
                startActivity(intent, transitionActivityOptions.toBundle())
            }
        })
}
