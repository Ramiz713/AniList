package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.manga.Serialization

class SerializationConverter {

    @TypeConverter
    fun serialize(listOfAuthors: List<Serialization>?): String =
        Gson().toJson(listOfAuthors, object : TypeToken<List<Serialization>>() {}.type)

    @TypeConverter
    fun deserialize(jsonOfAuthors: String): List<Serialization>? =
        Gson().fromJson(jsonOfAuthors, object : TypeToken<List<Serialization>>() {}.type)
}
