package com.itis2019.anilist.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.itis2019.anilist.api.JikanApiService
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.ui.animeList.AnimeListViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AnimeListViewModel::class.java) -> AnimeListViewModel(repository) as? T
                    ?: throw IllegalArgumentException("Unknown ViewModel class")

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
}
