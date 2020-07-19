package com.eventersapp.splash.onBoarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.eventersapp.base_android.OnBoardNavigator
import com.eventersapp.base_android.extensions.drawableArray
import com.eventersapp.base_android.extensions.stringsArray
import com.eventersapp.splash.OnBoardingPrefManager
import com.eventersapp.splash.R
import com.eventersapp.splash.databinding.FragmentOnboardingBinding

class OnBoardFragment(
    private val onBoardingPrefManager: OnBoardingPrefManager,
    private val appNavigator: OnBoardNavigator
) : Fragment(R.layout.fragment_onboarding) {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private lateinit var onBoardingList: ArrayList<OnBoardingModal>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentOnboardingBinding.bind(view)

        createOnBoardModal()

        with(binding.onboardViewpager) {
            adapter = OnBoardingAdapter(onBoardingList)
            addSlideChangeListener()
            binding.onboardPageIndicator.setViewPager2(this)
        }

        binding.onboardFab.setOnClickListener {
            if (binding.onboardViewpager.currentItem == onBoardingList.size - 1) {
                onBoardingPrefManager.isFirstTimeLaunch = false
                appNavigator.onBoardLogin()
            } else
                navigateToNextSlide()
        }
    }

    private fun createOnBoardModal() {
        onBoardingList = ArrayList<OnBoardingModal>()

        val imageArray = requireContext().drawableArray(R.array.onBoard_img_list)
        requireContext().stringsArray(R.array.onBoard_img_title).forEachIndexed { index, title ->
            onBoardingList.add(
                OnBoardingModal(
                    imageArray.getResourceId(index, -1),
                    title, requireContext().stringsArray(R.array.onBoard_img_description)[index]
                )
            )
        }
        imageArray.recycle()
    }

    private fun addSlideChangeListener() {
        binding.onboardViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                if (position == onBoardingList.size - 1)
                    binding.onboardFab.setImageResource(R.drawable.onboarding_complete)
                else
                    binding.onboardFab.setImageResource(R.drawable.onboarding_start)
            }
        })
    }

    private fun navigateToNextSlide() {
        val nextSlidePos: Int = binding.onboardViewpager.currentItem.plus(1)
        binding.onboardViewpager.setCurrentItem(nextSlidePos, true)
    }
}
