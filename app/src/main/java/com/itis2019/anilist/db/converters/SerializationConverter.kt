package com.itis2019.anilist.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.Serialization

class SerializationConverter {

    @TypeConverter
    fun serialize(listOfAuthors: List<Serialization>?): String =
        Gson().toJson(listOfAuthors, object : TypeToken<List<Serialization>>() {}.type)

    @TypeConverter
    fun deserialize(listOfAuthors: String): List<Serialization>? =
        Gson().fromJson(listOfAuthors, object : TypeToken<List<Serialization>>() {}.type)
}
