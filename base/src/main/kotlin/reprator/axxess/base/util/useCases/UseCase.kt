package reprator.axxess.base.util.useCases

interface UseCase<Type, in Params> {
     suspend fun run(params: Params): Type
}