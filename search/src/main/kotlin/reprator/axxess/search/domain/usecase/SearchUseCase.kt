package reprator.axxess.search.domain.usecase

import kotlinx.coroutines.flow.Flow
import reprator.axxess.base.util.useCases.AxxessResult
import reprator.axxess.base_android.SearchModal
import reprator.axxess.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(params: String): Flow<AxxessResult<List<SearchModal>>> {
        return searchRepository.searchItems(params)
    }
}
