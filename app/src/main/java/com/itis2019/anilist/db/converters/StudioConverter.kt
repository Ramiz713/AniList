package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.anime.Studio

class StudioConverter {

    @TypeConverter
    fun serialize(listOfStudios: List<Studio>?): String =
        Gson().toJson(listOfStudios, object : TypeToken<List<Studio>>() {}.type)

    @TypeConverter
    fun deserialize(listOfStudios: String?): List<Studio>? =
        Gson().fromJson(listOfStudios, object : TypeToken<List<Studio>>() {}.type)
}
