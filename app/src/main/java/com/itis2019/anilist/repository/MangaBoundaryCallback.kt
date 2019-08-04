package com.itis2019.anilist.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.itis2019.anilist.api.JikanApiService
import com.itis2019.anilist.db.MangaDao
import com.itis2019.anilist.entitites.MangaItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MangaBoundaryCallback(private val apiService: JikanApiService, private val mangaDao: MangaDao) :
    PagedList.BoundaryCallback<MangaItem>(), CoroutineScope, ResponseCallback {

    private var page = 1

    private val loadingData = MutableLiveData<Boolean>()
    private val errorData = MutableLiveData<Throwable>()

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override val isLoading: MutableLiveData<Boolean>
        get() = loadingData

    override val isError: MutableLiveData<Throwable>
        get() = errorData

    @Suppress("CheckResult")
    override fun onZeroItemsLoaded() {
        getTopAnimeList()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MangaItem) {
        getTopAnimeList()
    }

    private fun getTopAnimeList() =
        launch {
            invokeSuspend {
                val items = apiService.getTopMangaListAsync(page++).top
                mangaDao.insert(items)
            }
        }

    @Suppress("TooGenericExceptionCaught")
    suspend fun <T> invokeSuspend(suspendBlock: suspend () -> T): T? = try {
        loadingData.postValue(true)
        suspendBlock.invoke()
    } catch (throwable: Throwable) {
        errorData.postValue(throwable)
        null
    } finally {
        loadingData.postValue(false)
    }
}
