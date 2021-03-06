package com.itis2019.anilist.ui.animeList


import android.app.ActivityOptions
import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.itis2019.anilist.R
import com.itis2019.anilist.di.di
import com.itis2019.anilist.ui.AnimeDetailsActivity
import com.itis2019.anilist.ui.ViewModelFactory
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
        val view = inflater.inflate(R.layout.fragment_anime_list, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AnimeListViewModel::class.java)
        initRecycler(view)
        observeAnimeList(view)
        observeProgressBar()
        observeItemClick()
        return view
    }

    private fun observeAnimeList(view: View) =
        viewModel.onLoadNextPage().observe(this, Observer {
            when {
                it?.data != null -> adapter.submitList(it.data)
                it?.error != null -> Snackbar.make(view, it.error.localizedMessage, Snackbar.LENGTH_SHORT).show()
                else -> Snackbar.make(view, "We have problem!!!", Snackbar.LENGTH_SHORT).show()
            }
        })

    private fun initRecycler(view: View) {
        val manager = GridLayoutManager(activity, 2)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_anime)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    private fun observeProgressBar() =
        viewModel.isLoading().observe(this, Observer {
            if (it != null && it)
                progress_bar.visibility = View.VISIBLE
            else
                progress_bar.visibility = View.GONE
        })

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
