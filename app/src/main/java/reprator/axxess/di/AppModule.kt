package reprator.axxess.di

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import reprator.axxess.base.util.AppCoroutineDispatchers
import reprator.axxess.base.util.ConnectionDetector
import reprator.axxess.base_android.AppNavigator
import reprator.axxess.util.AppCoroutineDispatchersImpl
import reprator.axxess.util.AxxessAppNavigator
import reprator.axxess.util.connectivity.InternetChecker
import java.util.concurrent.Executors

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    fun provideAppNavigator(@ActivityContext context: Context): AppNavigator {
        return AxxessAppNavigator(context)
    }

    @Provides
    fun provideLifeCycle(): Lifecycle {
        return ProcessLifecycleOwner.get().lifecycle
    }

    @Provides
    fun provideLifetimeScope(lifecycle: Lifecycle): CoroutineScope {
        return lifecycle.coroutineScope
    }

    @Provides
    fun connectivityChecker(context: Context, lifecycle: Lifecycle): ConnectionDetector {
        return InternetChecker(context, lifecycle)
    }

    @Provides
    fun provideCoroutineDispatcherProvider(): AppCoroutineDispatchers {
        return AppCoroutineDispatchersImpl(
            Dispatchers.Main, Dispatchers.IO, Dispatchers.IO, Dispatchers.Default,
            Executors.newFixedThreadPool(1).asCoroutineDispatcher()
        )
    }
}