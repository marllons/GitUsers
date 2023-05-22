package com.marllons.gitusers

import android.app.Application
import com.marllons.gitusers.di.gitUsersModules
import com.marllons.mshttp.MsHttpModules
import com.marllons.mshttp.core.configuration.MSNetworkConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeConfig()
        initializeKoin()
    }

    private fun initializeConfig() {
        MSNetworkConfiguration.setup {
            debug { BuildConfig.DEBUG }
            versionCode { BuildConfig.VERSION_CODE }
            versionName { BuildConfig.VERSION_NAME }
            minVersionKey { BuildConfig.VERSION_NAME }
            baseUrl { BuildConfig.BASE_URL }
            appId { BuildConfig.APPLICATION_ID }
            buildType { BuildConfig.BUILD_TYPE }
        }
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(listOf(MsHttpModules.all, gitUsersModules))
        }
    }

}