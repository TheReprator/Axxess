package com.eventersapp.splash.onBoarding

import com.eventersapp.base.inject.FragmentScope
import com.eventersapp.base.inject.Holder
import com.eventersapp.base_android.AppNavigator
import com.eventersapp.base_android.OnBoardNavigator
import com.eventersapp.base_android.SplashNavigator
import com.eventersapp.base_android.util.di.FragmentComponent
import com.eventersapp.splash.OnBoardingPrefManager
import com.eventersapp.splash.di.SplashModule
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(
    modules = [
        OnBoardComponent.Provision::class,
        SplashModule::class
    ]
)
@FragmentScope
interface OnBoardComponent : FragmentComponent<OnBoardFragment> {
    @Subcomponent.Factory
    interface Factory : FragmentComponent.Factory<OnBoardComponent>

    @Module
    object Provision {
        @JvmStatic
        @Provides
        @FragmentScope
        fun fragment(
            holder: Holder<OnBoardFragment>,
            onBoardingPrefManager: OnBoardingPrefManager,
            appNavigator: AppNavigator
        ): OnBoardFragment {
            return OnBoardFragment(
                onBoardingPrefManager,
                appNavigator
            ).also {
                holder.instance = it
            }
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun holder(): Holder<OnBoardFragment> = Holder()

        @Provides
        @FragmentScope
        fun provideOnBoardNavigator(
            appNavigator: AppNavigator
        ): OnBoardNavigator {
            return appNavigator
        }
    }
}
