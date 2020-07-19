package reprator.axxess.base_android

interface AppNavigator: SearchNavigator

interface SearchNavigator{
    fun navigateToItemDetail(searchItem: SearchModal)
}