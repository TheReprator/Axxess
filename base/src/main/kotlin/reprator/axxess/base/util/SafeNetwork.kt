package reprator.axxess.base.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import reprator.axxess.base.util.useCases.AxxessResult
import reprator.axxess.base.util.useCases.ErrorResult

suspend fun <T : Any> safeApiCall(
    call: suspend () -> Flow<AxxessResult<T>>,
    errorMessage: String? = null
): Flow<AxxessResult<T>> {
    return try {
        call()
    } catch (e: Exception) {
        println(e.printStackTrace())
        flow {
            emit(ErrorResult(message = errorMessage ?: e.message))
        }
    }
}