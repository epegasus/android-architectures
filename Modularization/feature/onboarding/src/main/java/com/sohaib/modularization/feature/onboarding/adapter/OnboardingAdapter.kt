package com.sohaib.modularization.feature.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sohaib.modularization.core.extensions.loadImage
import com.sohaib.modularization.domain.onboarding.entity.OnBoardingStep
import com.sohaib.modularization.feature.onboarding.databinding.ItemOnboardingBinding

/**
 * Adapter for onboarding steps
 */
class OnboardingAdapter : ListAdapter<OnBoardingStep, OnboardingAdapter.OnboardingViewHolder>(OnboardingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val binding = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class OnboardingViewHolder(private val binding: ItemOnboardingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(step: OnBoardingStep) {
            binding.sivThumbnail.loadImage(step.imageRes)
            binding.mtvStepTitle.text = step.title
            binding.mtvStepBody.text = step.description
        }
    }

    class OnboardingDiffCallback : DiffUtil.ItemCallback<OnBoardingStep>() {
        override fun areItemsTheSame(oldItem: OnBoardingStep, newItem: OnBoardingStep): Boolean {
            return oldItem.stepNumber == newItem.stepNumber
        }

        override fun areContentsTheSame(oldItem: OnBoardingStep, newItem: OnBoardingStep): Boolean {
            return oldItem == newItem
        }
    }
}
