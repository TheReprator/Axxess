package com.eventersapp.splash.di

import com.eventer.realmDbuser.useCase.DeleteUserUseCase
import com.eventer.realmDbuser.useCase.FetchUserUseCase
import com.eventersapp.base.inject.FragmentScope
import com.eventersapp.base.inject.Holder
import com.eventersapp.base.util.AppCoroutineDispatchers
import com.eventersapp.base_android.AppNavigator
import com.eventersapp.base_android.util.di.FragmentComponent
import com.eventersapp.splash.OnBoardingPrefManager
import com.eventersapp.splash.SplashFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SplashComponent.Provision::class,
        SplashModule::class
    ]
)
@FragmentScope
interface SplashComponent : FragmentComponent<SplashFragment> {
    @Subcomponent.Factory
    interface Factory : FragmentComponent.Factory<SplashComponent>

    @Module
    object Provision {
        @JvmStatic
        @Provides
        @FragmentScope
        fun fragment(
            holder: Holder<SplashFragment>,
            onBoardingPrefManager: OnBoardingPrefManager,
            coroutinesDispatcherProvider: AppCoroutineDispatchers,
            fetchUserUseCase: FetchUserUseCase,
            deleteUserUseCase: DeleteUserUseCase,
            appNavigator: AppNavigator
        ): SplashFragment {
            return SplashFragment(
                onBoardingPrefManager,
                coroutinesDispatcherProvider,
                fetchUserUseCase,
                deleteUserUseCase,
                appNavigator,
                holder
            )
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun holder(): Holder<SplashFragment> = Holder()
    }
}
