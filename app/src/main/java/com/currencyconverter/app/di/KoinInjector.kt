package com.currencyconverter.app.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import currencyconverter.common.data.di.injectDataModule

object KoinInjector {

    fun inject(context: Context){
        startKoin {
            androidContext(context)
        }
        injectDataModule()
    }

}