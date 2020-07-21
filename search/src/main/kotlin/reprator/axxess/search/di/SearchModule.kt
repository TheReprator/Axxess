package reprator.axxess.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import reprator.axxess.base.util.ConnectionDetector
import reprator.axxess.base_android.AppNavigator
import reprator.axxess.base_android.SearchNavigator
import reprator.axxess.search.data.datasource.SearchRemoteDataSource
import reprator.axxess.search.data.repository.SearchRepositoryImpl
import reprator.axxess.search.datasource.SearchRemoteDataSourceImpl
import reprator.axxess.search.datasource.remote.SearchApiService
import reprator.axxess.search.datasource.remote.modal.remoteMapper.SearchMapper
import reprator.axxess.search.domain.repository.SearchRepository
import reprator.axxess.search.domain.usecase.SearchUseCase
import retrofit2.Retrofit

@InstallIn(ActivityComponent::class)
@Module
class SearchModule {

    @Provides
    fun provideSearchNavigator(appNavigator: AppNavigator): SearchNavigator {
        return appNavigator
    }

    @Provides
    fun provideSearchRemoteDataSource(
        mainApiService: SearchApiService, tabMapper: SearchMapper
    ): SearchRemoteDataSource {
        return SearchRemoteDataSourceImpl(
            mainApiService,
            tabMapper
        )
    }

    @Provides
    fun provideSearchRepository(
        verifyRemoteDataSource: SearchRemoteDataSource, connectionDetector: ConnectionDetector
    ): SearchRepository {
        return SearchRepositoryImpl(
            verifyRemoteDataSource,
            connectionDetector
        )
    }

    @Provides
    fun provideSearchUseCase(
        timeLineRepository: SearchRepository
    ): SearchUseCase {
        return SearchUseCase(timeLineRepository)
    }

    @Provides
    fun provideSearchApiService(
        retrofit: Retrofit
    ): SearchApiService {
        return retrofit
            .create(SearchApiService::class.java)
    }
}