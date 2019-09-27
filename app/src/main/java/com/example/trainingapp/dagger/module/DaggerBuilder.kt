package com.example.trainingapp.dagger.module

import com.example.trainingapp.view.MainActivity
import com.example.trainingapp.view.fragments.DetailsFragment
import com.example.trainingapp.view.fragments.MainListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DaggerBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindMainListFragment(): MainListFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment
}