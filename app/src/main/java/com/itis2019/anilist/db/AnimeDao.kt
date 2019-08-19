package com.itis2019.anilist.db

import androidx.paging.DataSource
import androidx.room.*
import com.itis2019.anilist.entitites.anime.AnimeItem

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime_data ORDER BY rank")
    fun getAll(): DataSource.Factory<Int, AnimeItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(animeData: List<AnimeItem>)

    @Update
    fun update(anime: AnimeItem)

    @Query("DELETE FROM anime_data")
    fun deleteAll()
}
