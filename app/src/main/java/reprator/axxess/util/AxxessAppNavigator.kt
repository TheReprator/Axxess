package reprator.axxess.util

import android.app.Activity
import android.content.Context
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import dagger.hilt.android.qualifiers.ActivityContext
import reprator.axxess.R
import reprator.axxess.base_android.AppNavigator
import reprator.axxess.base_android.SearchModal
import reprator.axxess.search.SearchFragmentDirections
import javax.inject.Inject

class AxxessAppNavigator @Inject constructor(
    @ActivityContext context: Context
) : AppNavigator {

    private val activity: Activity = context as Activity

    private fun getNavController() =
        activity.findNavController(R.id.fragment_nav_host)

    private fun directions(navDirections: NavDirections) {
        getNavController().navigate(navDirections)
    }

    override fun navigateToItemDetail(searchItem: SearchModal) {
        val action = SearchFragmentDirections.actionNavigationSearchToNavigationItemDetail(searchItem)
        directions(action)
    }

    override fun navigateToBack() {
        getNavController().navigateUp()
    }
}
