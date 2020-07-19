package reprator.axxess.search.datasource.remote

import reprator.axxess.search.datasource.remote.modal.SearchEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchApiService {

    @GET("gallery/search/1")
    suspend fun searchItemList(@Header("Authorization") header: String, @Query("q") searchItem: String): Response<List<SearchEntity>>
}