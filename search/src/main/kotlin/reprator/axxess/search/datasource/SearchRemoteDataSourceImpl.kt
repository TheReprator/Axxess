package reprator.axxess.search.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import reprator.axxess.base.util.safeApiCall
import reprator.axxess.base.util.useCases.AxxessResult
import reprator.axxess.base.util.useCases.Success
import reprator.axxess.base_android.SearchModal
import reprator.axxess.search.data.datasource.SearchRemoteDataSource
import reprator.axxess.search.datasource.remote.SearchApiService
import reprator.axxess.search.datasource.remote.modal.remoteMapper.SearchMapper
import reprator.axxess.search.toResult
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val searchApiService: SearchApiService, private val searchMapper: SearchMapper
) : SearchRemoteDataSource {

    override suspend fun searchItem(search: String): Flow<AxxessResult<List<SearchModal>>> =
        safeApiCall(call = { searchItemApi(search) })

    private suspend fun searchItemApi(search: String): Flow<AxxessResult<List<SearchModal>>> {
        return flow {
            searchApiService.searchItemList(HEADER, search).toResult {
                emit(Success(it.map { searchMapper.map(it) }))
            }
        }
    }

    companion object {
        private const val HEADER = " Client-ID 137cda6b5008a7c"
    }
}

