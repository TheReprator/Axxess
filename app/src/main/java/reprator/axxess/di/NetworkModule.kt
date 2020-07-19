package reprator.axxess.di

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import dagger.Module
import dagger.Provides
import dagger.Lazy
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import reprator.axxess.BuildConfig
import reprator.axxess.base.util.isNotNull
import reprator.axxess.util.retrofit.converter.EnvelopeConverterFactory

private const val CONNECTION_TIME = 90L

@InstallIn(ApplicationComponent::class)
@Module(
    includes = [
        JackSonModule::class
    ]
)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        if (!isDebug()) {
            return null
        }

        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideHttpEventListener(): LoggingEventListener.Factory? {
        if (!isDebug()) {
            return null
        }

        return LoggingEventListener.Factory()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor?,
        loggingEventListener: LoggingEventListener.Factory?
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (httpLoggingInterceptor.isNotNull()) {
                    addInterceptor(httpLoggingInterceptor)
                }
                if (loggingEventListener.isNotNull()) {
                    eventListenerFactory(loggingEventListener)
                }

                connectTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
                readTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
                writeTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
                followRedirects(true)
                followSslRedirects(true)
                cache(null)
                retryOnConnectionFailure(false)
            }.connectionPool(ConnectionPool(10, 2, TimeUnit.MINUTES))
            .dispatcher(
                Dispatcher().apply {
                    // Allow for high number of concurrent image fetches on same host.
                    maxRequestsPerHost = 8
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun createRetrofit(
        okHttpClient: Lazy<OkHttpClient>,
        envelopeConverterFactory: EnvelopeConverterFactory,
        converterFactory: JacksonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl())
            .client(okHttpClient.get())
            .addConverterFactory(envelopeConverterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun baseUrl() = BuildConfig.HOST

    private fun isDebug() = BuildConfig.DEBUG
}
