package com.itis2019.anilist.di

import android.app.Application
import com.itis2019.anilist.di.module.appModule
import com.itis2019.anilist.di.module.netModule
import com.itis2019.anilist.di.module.repositoryModule
import com.itis2019.anilist.di.module.roomModule
import com.itis2019.anilist.ui.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

lateinit var di: Kodein

fun initKodein(app: Application) {
    di = Kodein {
        import(appModule(app))
        import(netModule())
        import(roomModule())
        import(repositoryModule())
        bind<ViewModelFactory>() with singleton { ViewModelFactory(instance()) }
    }
}
