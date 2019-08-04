package com.itis2019.anilist.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.itis2019.anilist.entitites.MangaItem

@Dao
interface MangaDao {

    @Query("SELECT * FROM manga_data ORDER BY rank")
    fun getAll(): DataSource.Factory<Int, MangaItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mangaData: List<MangaItem>)

    @Query("DELETE FROM manga_data")
    fun deleteAll()
}
