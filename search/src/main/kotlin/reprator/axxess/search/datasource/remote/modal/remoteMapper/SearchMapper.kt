package reprator.axxess.search.datasource.remote.modal.remoteMapper

import reprator.axxess.base.util.Mapper
import reprator.axxess.base_android.SearchModal
import reprator.axxess.search.datasource.remote.modal.SearchEntity
import javax.inject.Inject

class SearchMapper @Inject constructor(
) : Mapper<SearchEntity, SearchModal> {

    override suspend fun map(from: SearchEntity): SearchModal {
        return SearchModal(
            from.id, from.title.orEmpty(), from.images.firstOrNull {
                it.link != null
            }?.link.orEmpty()
        )
    }
}