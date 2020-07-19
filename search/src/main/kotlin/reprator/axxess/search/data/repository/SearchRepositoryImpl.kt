package reprator.axxess.search.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import reprator.axxess.base.util.ConnectionDetector
import reprator.axxess.base.util.useCases.AxxessResult
import reprator.axxess.base.util.useCases.ErrorResult
import reprator.axxess.base_android.SearchModal
import reprator.axxess.search.data.datasource.SearchRemoteDataSource
import reprator.axxess.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val connectRemoteDataSource: SearchRemoteDataSource,
    private val connectionDetector: ConnectionDetector
) : SearchRepository {

    override suspend fun searchItems(search: String): Flow<AxxessResult<List<SearchModal>>> {
        return if (connectionDetector.isInternetAvailable)
            connectRemoteDataSource.searchItem(search)
        else
            flow {
                emit(ErrorResult(message = "No internet connection."))
            }
    }
}