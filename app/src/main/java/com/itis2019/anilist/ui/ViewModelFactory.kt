package com.itis2019.anilist.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.ui.animeList.AnimeListViewModel
import com.itis2019.anilist.ui.mangaList.MangaListViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AnimeListViewModel::class.java) -> AnimeListViewModel(repository) as T
            modelClass.isAssignableFrom(MangaListViewModel::class.java) -> MangaListViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
}
