package reprator.axxess.base.extensions

import reprator.axxess.base.util.AppCoroutineDispatchers
import reprator.axxess.base.util.isNull
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.mainBlock(
    coroutinesDispatcherProvider: AppCoroutineDispatchers,
    block: suspend CoroutineScope.() -> Unit
) {
    launch(coroutinesDispatcherProvider.main) {
        block(this)
    }
}

fun CoroutineScope.computationalBlock(
    coroutinesDispatcherProvider: AppCoroutineDispatchers,
    coroutineExceptionHandler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> Unit
) {
    val type = if (coroutineExceptionHandler.isNull())
        coroutinesDispatcherProvider.io
    else
        coroutinesDispatcherProvider.io + coroutineExceptionHandler

    launch(type) {
        block.invoke(this)
    }
}