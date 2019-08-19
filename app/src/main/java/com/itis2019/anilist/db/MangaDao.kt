package com.itis2019.anilist.db

import androidx.paging.DataSource
import androidx.room.*
import com.itis2019.anilist.entitites.manga.MangaItem

@Dao
interface MangaDao {

    @Query("SELECT * FROM manga_data ORDER BY rank")
    fun getAll(): DataSource.Factory<Int, MangaItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(mangaData: List<MangaItem>)

    @Update
    fun update(manga: MangaItem)

    @Query("DELETE FROM manga_data")
    fun deleteAll()
}
