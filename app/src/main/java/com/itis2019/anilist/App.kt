package com.itis2019.anilist

import android.app.Application
import com.itis2019.anilist.di.initKodein

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKodein(this)
    }
}
