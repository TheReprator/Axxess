package reprator.axxess.search.data.datasource

import kotlinx.coroutines.flow.Flow
import reprator.axxess.base.util.useCases.AxxessResult
import reprator.axxess.base_android.SearchModal

interface SearchRemoteDataSource {
    suspend fun searchItem(search: String): Flow<AxxessResult<List<SearchModal>>>
}