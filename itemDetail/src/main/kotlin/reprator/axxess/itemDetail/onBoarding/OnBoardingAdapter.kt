package com.eventersapp.splash.onBoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eventersapp.splash.R
import com.eventersapp.splash.databinding.RowOnboardingBinding


class OnBoardingAdapter(private val onBoardingModalList: List<OnBoardingModal>) :
    RecyclerView.Adapter<VHOnBoarding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHOnBoarding {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RowOnboardingBinding.inflate(inflater, parent, false)

        return VHOnBoarding(binding)
    }

    override fun onBindViewHolder(holder: VHOnBoarding, position: Int) {
        holder.bind(onBoardingModalList[position])
    }

    override fun getItemCount(): Int = onBoardingModalList.size
}

class VHOnBoarding constructor(val root: RowOnboardingBinding) :
    RecyclerView.ViewHolder(root.root) {
    fun bind(onBoardingModal: OnBoardingModal) {
        root.onBoardModal = onBoardingModal
        root.executePendingBindings()
    }

}