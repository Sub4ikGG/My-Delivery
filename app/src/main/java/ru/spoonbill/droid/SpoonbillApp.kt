package ru.spoonbill.droid

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.spoonbill.droid.di.viewModelModule
import ru.spoonbill.droid.data.core.di.dataModule

class SpoonbillApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpoonbillApp)
            modules(
                listOf(
                    viewModelModule,
                    dataModule,
                )
            )
        }
    }
}