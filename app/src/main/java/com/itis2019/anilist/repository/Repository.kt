package com.itis2019.anilist.repository

import android.arch.paging.PagedList
import com.itis2019.anilist.entitites.AnimeItem
import io.reactivex.Observable

interface Repository{
    fun getAnimePage() : Observable<PagedList<AnimeItem>>
}
