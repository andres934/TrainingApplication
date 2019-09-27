package com.example.trainingapp.dagger.components

import android.app.Application
import com.example.trainingapp.App
import com.example.trainingapp.dagger.module.DaggerBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    DaggerBuilder::class])
interface AppComponent: AndroidInjector<App> {

    override fun inject(instance: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}