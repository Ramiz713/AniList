package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.anime.Producer

class ProducersConverter {

    @TypeConverter
    fun serialize(listOfProducers: List<Producer>?): String =
        Gson().toJson(listOfProducers, object : TypeToken<List<Producer>>() {}.type)

    @TypeConverter
    fun deserialize(listOfProducers: String?): List<Producer>? =
        Gson().fromJson(listOfProducers, object : TypeToken<List<Producer>>() {}.type)
}
