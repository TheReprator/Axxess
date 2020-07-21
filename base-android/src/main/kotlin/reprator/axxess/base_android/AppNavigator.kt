package reprator.axxess.base_android

interface AppNavigator: SearchNavigator, BackNavigator

interface SearchNavigator{
    fun navigateToItemDetail(searchItem: SearchModal)
}

interface BackNavigator{
    fun navigateToBack()
}