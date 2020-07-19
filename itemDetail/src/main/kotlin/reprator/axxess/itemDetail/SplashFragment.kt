package com.eventersapp.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.eventer.realmDbuser.useCase.DeleteUserUseCase
import com.eventer.realmDbuser.useCase.FetchUserUseCase
import com.eventersapp.base.util.useCases.ErrorResult
import com.eventersapp.base.util.useCases.Success
import com.eventersapp.base.extensions.mainBlock
import com.eventersapp.base.inject.Holder
import com.eventersapp.base.util.AppCoroutineDispatchers
import com.eventersapp.base_android.SplashNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment (
    private val onBoardingPrefManager: OnBoardingPrefManager,
    private val coroutinesDispatcherProvider: AppCoroutineDispatchers,
    private val fetchUserUseCase: FetchUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val appNavigator: SplashNavigator,
    private val holder: Holder<SplashFragment>
) : Fragment(R.layout.fragment_splash) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        holder.instance = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.mainBlock(coroutinesDispatcherProvider) {

            delay(3000)

            if (onBoardingPrefManager.isFirstTimeLaunch) {
                appNavigator.onSplashOnBoard()
            } else {
                checkWhetherUserExist()
            }
        }
    }

    private fun checkWhetherUserExist() {
        lifecycle.coroutineScope.launch(coroutinesDispatcherProvider.main) {
            when (val userDetail = fetchUserUseCase.userDetail()) {
                is Success -> {
                    if (userDetail.data.userId.isBlank()) {
                        deleteUserUseCase.deleteDetail()
                        appNavigator.onSplashLogin()
                    } else
                        appNavigator.onSplashDashBoard()
                }
                is ErrorResult -> {
                    appNavigator.onSplashLogin()
                }
            }
        }
    }
}
