package reprator.axxess.base.util.useCases

sealed class AxxessResult<out T> {
    open fun get(): T? = null
}

data class Success<T>(val data: T, val responseModified: Boolean = true) : AxxessResult<T>() {
    override fun get(): T = data
}

data class ErrorResult(
    val throwable: Throwable? = null,
    val message: String? = null
) : AxxessResult<Nothing>()

