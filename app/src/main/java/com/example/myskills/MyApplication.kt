package com.example.myskills

import android.app.Application
import com.example.myskills.MainViewModel
import com.example.myskills.repository.room.RoomRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val module = module {
            single { this@MyApplication }
            single { RoomRepository(get()) }
            viewModel { MainViewModel() }

        }

        startKoin {
            androidContext(applicationContext)
            modules(module)
        }

    }
}