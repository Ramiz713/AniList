package com.itis2019.anilist.ui.mangaList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.itis2019.anilist.R
import com.itis2019.anilist.di.di
import com.itis2019.anilist.ui.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_manga_list.*
import org.kodein.di.generic.instance

class MangaListFragment : Fragment() {

    private lateinit var imageView: ImageView
    private val viewModelFactory: ViewModelFactory by di.instance()
    private lateinit var viewModel: MangaListViewModel

    private val adapter = MangaAdapter { pair ->
        imageView = pair.second
        viewModel.movieClicked(pair)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MangaListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_manga_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
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
            com.google.android.material.snackbar.Snackbar.make(
                view,
                it?.localizedMessage ?: "Problems",
                com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    private fun observeAnimeList() =
        viewModel.onLoadNextPage().observe(this, Observer {
            adapter.submitList(it)
        })

    private fun initRecycler() {
        val manager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
            2,
            androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
        )
        val recyclerView = rv_manga as androidx.recyclerview.widget.RecyclerView
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    private fun observeItemClick() =
        viewModel.navigateToAnimeDetails.observe(this, Observer {
            it?.let { pair ->
            }
        })
}
