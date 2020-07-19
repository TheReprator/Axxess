package com.eventersapp.splash

import com.eventersapp.base_android.util.sPrefs.SharedPreferenceManager
import javax.inject.Inject

interface OnBoardingPrefManager {
    var isFirstTimeLaunch: Boolean
}

class OnBoardingPrefManagerImpl @Inject constructor(
    private val preferenceManager: SharedPreferenceManager
) : OnBoardingPrefManager {

    override var isFirstTimeLaunch: Boolean
        get() {
            return preferenceManager[IS_FIRST_TIME_LAUNCH, true]!!
        }
        set(isFirstTime) {
            preferenceManager[IS_FIRST_TIME_LAUNCH] =  isFirstTime
        }

    companion object {
        const val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"
    }

}