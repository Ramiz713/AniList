package com.itis2019.anilist.ui.animeDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis2019.anilist.entitites.anime.AnimeItem
import com.itis2019.anilist.repository.Repository
import com.itis2019.anilist.ui.base.BaseViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AnimeDetailsViewModel(private val repository: Repository) : BaseViewModel(), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val animeItem = MutableLiveData<AnimeItem>()

    fun getAnimeById(id: Int): LiveData<AnimeItem> {
        launch {
            invokeSuspend {
                val item = repository.getAnimeItem(id)
                withContext(Dispatchers.Main) {
                    animeItem.value = item
                }
            }
        }
        return animeItem
    }

}
