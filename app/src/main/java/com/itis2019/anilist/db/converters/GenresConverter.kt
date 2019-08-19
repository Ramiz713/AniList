package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.manga.Genre

class GenresConverter {

    @TypeConverter
    fun serialize(listOfGenres: List<Genre>?): String =
        Gson().toJson(listOfGenres, object : TypeToken<List<Genre>>() {}.type)

    @TypeConverter
    fun deserialize(jsonOfGenres: String?): List<Genre>? =
        Gson().fromJson(jsonOfGenres, object : TypeToken<List<Genre>>() {}.type)
}
