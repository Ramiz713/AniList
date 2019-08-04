package com.itis2019.anilist.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.itis2019.anilist.entitites.AnimeItem

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime_data ORDER BY rank")
    fun getAll(): DataSource.Factory<Int, AnimeItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animeData : List<AnimeItem>)

    @Query("DELETE FROM anime_data")
    fun deleteAll()
}
