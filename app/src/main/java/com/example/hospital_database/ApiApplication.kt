package com.example.hospital_database

import android.app.Application
import com.example.hospital_database.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class ApiApplication : Application(){
    companion object {
        lateinit var self: Application
        fun applicationContext(): Application {
            return self
        }
    }

    init {
        self = this
    }
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val module = listOf(
            apiModule
        )

        startKoin {
//            androidLogger()
            androidContext(this@ApiApplication)
            modules(module)
        }
    }
}