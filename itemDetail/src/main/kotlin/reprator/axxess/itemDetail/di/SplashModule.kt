package com.eventersapp.splash.di

import android.content.SharedPreferences
import com.eventersapp.base.inject.FragmentScope
import com.eventersapp.base_android.AppNavigator
import com.eventersapp.base_android.SplashNavigator
import com.eventersapp.base_android.util.sPrefs.SharedPreferenceManager
import com.eventersapp.splash.OnBoardingPrefManager
import com.eventersapp.splash.OnBoardingPrefManagerImpl
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    @FragmentScope
    fun provideSharedPreferenceManager(
        sharedPreferences: SharedPreferences
    ): SharedPreferenceManager {
        return SharedPreferenceManager(sharedPreferences)
    }

    @Provides
    @FragmentScope
    fun provideOnBoardingPrefManager(
        sharedPreferenceManager: SharedPreferenceManager
    ): OnBoardingPrefManager {
        return OnBoardingPrefManagerImpl(sharedPreferenceManager)
    }

    @Provides
    @FragmentScope
    fun provideSplashNavigator(
        appNavigator: AppNavigator
    ): SplashNavigator {
        return appNavigator
    }
}