package com.itis2019.anilist.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.Author

class AuthorsConverter {

    @TypeConverter
    fun serialize(listOfAuthors: List<Author>?): String =
        Gson().toJson(listOfAuthors, object : TypeToken<List<Author>>() {}.type)

    @TypeConverter
    fun deserialize(listOfAuthors: String?): List<Author>? =
        Gson().fromJson(listOfAuthors, object : TypeToken<List<Author>>() {}.type)
}
