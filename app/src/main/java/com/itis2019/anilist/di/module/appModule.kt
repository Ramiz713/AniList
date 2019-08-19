package com.itis2019.anilist.di.module

import android.app.Application
import android.content.Context
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

fun appModule(app: Application) = Module("module name") {

    bind<Context>() with singleton { app }
}
