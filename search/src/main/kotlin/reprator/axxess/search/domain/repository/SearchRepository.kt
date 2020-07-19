package reprator.axxess.search.domain.repository

import kotlinx.coroutines.flow.Flow
import reprator.axxess.base.util.useCases.AxxessResult
import reprator.axxess.base_android.SearchModal

interface SearchRepository {
    suspend fun searchItems(search: String): Flow<AxxessResult<List<SearchModal>>>
}