package com.linecorp.devdaysched

import android.app.Application
import com.linecorp.devdaysched.di.newsModule
import com.linecorp.devdaysched.di.speakerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DevDaySchedApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // inject koin module
        startKoin {
            fileProperties()
            androidLogger(Level.DEBUG)
            androidContext(this@DevDaySchedApplication)
            modules(speakerModule + newsModule)
        }
    }
}
