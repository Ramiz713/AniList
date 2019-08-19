package com.itis2019.anilist.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2019.anilist.entitites.manga.Author

class AuthorsConverter {

    @TypeConverter
    fun serialize(listOfAuthors: List<Author>?): String =
        Gson().toJson(listOfAuthors, object : TypeToken<List<Author>>() {}.type)

    @TypeConverter
    fun deserialize(jsonOfAuthors: String?): List<Author>? =
        Gson().fromJson(jsonOfAuthors, object : TypeToken<List<Author>>() {}.type)
}
