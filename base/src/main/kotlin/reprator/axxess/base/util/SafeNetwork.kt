package reprator.axxess.base.util

import reprator.axxess.base.util.useCases.ErrorResult
import reprator.axxess.base.util.useCases.AxxessResult

suspend fun <T : Any> safeApiCall(
    call: suspend () -> AxxessResult<T>,
    errorMessage: String? = null
): AxxessResult<T> {
    return try {
        call()
    } catch (e: Exception) {
        println(e.printStackTrace())
        ErrorResult(message = errorMessage ?: e.message)
    }
}