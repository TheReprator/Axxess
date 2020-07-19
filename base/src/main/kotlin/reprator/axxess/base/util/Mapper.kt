package reprator.axxess.base.util

interface Mapper<in InputModal, out OutputModal> {
    suspend fun map(from: InputModal): OutputModal
}

interface MapperFromTo<InputModal, OutputModal> {
    suspend fun mapFrom(from: InputModal): OutputModal
    suspend fun mapTo(from: OutputModal): InputModal
}

fun <I, O> mapList(input: List<I>, mapListItem: (I) -> O): List<O> {
    return input.map { mapListItem(it) }
}

fun <F, T> Mapper<F, T>.toListMapper(): suspend (List<F>) -> List<T> {
    return { list -> list.map { item -> map(item) } }
}

fun <F, T> Mapper<F, T>.forLists(): suspend (List<F>) -> List<T> {
    return { list -> list.map { item -> map(item) } }
}

fun <F, T> MapperFromTo<F, T>.toListMapperFrom(): suspend (List<F>) -> List<T> {
    return { list -> list.map { item -> mapFrom(item) } }
}

fun <F, T> MapperFromTo<F, T>.toListMapperTo(): suspend (List<T>) -> List<F> {
    return { list -> list.map { item -> mapTo(item) } }
}
