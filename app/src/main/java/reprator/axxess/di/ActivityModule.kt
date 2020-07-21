package reprator.axxess.di

import android.content.Context
import androidx.lifecycle.Lifecycle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import reprator.axxess.base.util.ConnectionDetector
import reprator.axxess.base_android.AppNavigator
import reprator.axxess.util.AxxessAppNavigator
import reprator.axxess.util.connectivity.InternetChecker

@InstallIn(ActivityComponent::class)
@Module
class ActivityModule {
    @Provides
    fun provideAppNavigator(@ActivityContext context: Context): AppNavigator {
        return AxxessAppNavigator(context)
    }

    @Provides
    fun connectivityChecker(@ApplicationContext context: Context, lifecycle: Lifecycle): ConnectionDetector {
        return InternetChecker(context, lifecycle)
    }
}