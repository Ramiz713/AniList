package com.itis2019.anilist.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.Genre

class GenresConverter {

    @TypeConverter
    fun serialize(listOfGenres: List<Genre>?): String =
        Gson().toJson(listOfGenres, object : TypeToken<List<Genre>>() {}.type)

    @TypeConverter
    fun deserialize(listOfGenres: String?): List<Genre>? =
        Gson().fromJson(listOfGenres, object : TypeToken<List<Genre>>() {}.type)
}
