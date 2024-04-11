package com.msoula.noteapp.di

import com.msoula.noteapp.core.navigation.Navigator
import com.msoula.noteapp.core.navigation.NavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = NavigatorImpl()
}