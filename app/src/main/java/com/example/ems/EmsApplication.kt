package com.example.ems

import android.app.Application
import com.example.ems.di.energyModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmsApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@EmsApplication)
      modules(energyModule)
    }
  }
}