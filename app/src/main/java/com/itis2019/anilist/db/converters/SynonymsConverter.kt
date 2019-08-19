package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SynonymsConverter {

    @TypeConverter
    fun serialize(listOfEpisodes: List<String>?): String =
        Gson().toJson(listOfEpisodes, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun deserialize(jsonOfEpisodes: String?): List<String>? =
        Gson().fromJson(jsonOfEpisodes, object : TypeToken<List<String>>() {}.type)
}
