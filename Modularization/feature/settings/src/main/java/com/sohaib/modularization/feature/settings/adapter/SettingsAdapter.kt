package com.sohaib.modularization.feature.settings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sohaib.modularization.feature.settings.databinding.ItemSettingsActionBinding
import com.sohaib.modularization.feature.settings.databinding.ItemSettingsHeaderBinding
import com.sohaib.modularization.feature.settings.databinding.ItemSettingsInfoBinding
import com.sohaib.modularization.feature.settings.databinding.ItemSettingsSingleChoiceBinding
import com.sohaib.modularization.feature.settings.databinding.ItemSettingsSliderBinding
import com.sohaib.modularization.feature.settings.databinding.ItemSettingsSwitchBinding
import com.sohaib.modularization.feature.settings.ui.SettingsItem

/**
 * Adapter for settings list
 */
class SettingsAdapter : ListAdapter<SettingsItem, RecyclerView.ViewHolder>(SettingsDiffCallback()) {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_SWITCH = 1
        private const val TYPE_SINGLE_CHOICE = 2
        private const val TYPE_SLIDER = 3
        private const val TYPE_INFO = 4
        private const val TYPE_ACTION = 5
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SettingsItem.Header -> TYPE_HEADER
            is SettingsItem.Switch -> TYPE_SWITCH
            is SettingsItem.SingleChoice -> TYPE_SINGLE_CHOICE
            is SettingsItem.Slider -> TYPE_SLIDER
            is SettingsItem.Info -> TYPE_INFO
            is SettingsItem.Action -> TYPE_ACTION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ItemSettingsHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(binding)
            }

            TYPE_SWITCH -> {
                val binding = ItemSettingsSwitchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SwitchViewHolder(binding)
            }

            TYPE_SINGLE_CHOICE -> {
                val binding = ItemSettingsSingleChoiceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SingleChoiceViewHolder(binding)
            }

            TYPE_SLIDER -> {
                val binding = ItemSettingsSliderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SliderViewHolder(binding)
            }

            TYPE_INFO -> {
                val binding = ItemSettingsInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                InfoViewHolder(binding)
            }

            TYPE_ACTION -> {
                val binding = ItemSettingsActionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ActionViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is SettingsItem.Header -> (holder as HeaderViewHolder).bind(item)
            is SettingsItem.Switch -> (holder as SwitchViewHolder).bind(item)
            is SettingsItem.SingleChoice -> (holder as SingleChoiceViewHolder).bind(item)
            is SettingsItem.Slider -> (holder as SliderViewHolder).bind(item)
            is SettingsItem.Info -> (holder as InfoViewHolder).bind(item)
            is SettingsItem.Action -> (holder as ActionViewHolder).bind(item)
        }
    }

    class HeaderViewHolder(
        private val binding: ItemSettingsHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingsItem.Header) {
            binding.tvHeader.text = item.title
        }
    }

    class SwitchViewHolder(
        private val binding: ItemSettingsSwitchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingsItem.Switch) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.switchSetting.isChecked = item.isChecked
            binding.switchSetting.setOnCheckedChangeListener { _, isChecked ->
                // Handle switch change
            }
        }
    }

    class SingleChoiceViewHolder(
        private val binding: ItemSettingsSingleChoiceBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingsItem.SingleChoice) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvValue.text = item.selectedValue
            binding.root.setOnClickListener {
                // Handle single choice selection
            }
        }
    }

    class SliderViewHolder(
        private val binding: ItemSettingsSliderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingsItem.Slider) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.slider.valueFrom = item.min.toFloat()
            binding.slider.valueTo = item.max.toFloat()
            binding.slider.value = item.value.toFloat()
            binding.tvValue.text = item.value.toString()
            binding.slider.addOnChangeListener { _, value, _ ->
                binding.tvValue.text = value.toInt().toString()
            }
        }
    }

    class InfoViewHolder(
        private val binding: ItemSettingsInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingsItem.Info) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvValue.text = item.value
        }
    }

    class ActionViewHolder(
        private val binding: ItemSettingsActionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingsItem.Action) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.btnAction.text = item.actionText
            binding.btnAction.setOnClickListener {
                // Handle action click
            }
        }
    }

    class SettingsDiffCallback : DiffUtil.ItemCallback<SettingsItem>() {
        override fun areItemsTheSame(oldItem: SettingsItem, newItem: SettingsItem): Boolean {
            return when {
                oldItem is SettingsItem.Header && newItem is SettingsItem.Header ->
                    oldItem.title == newItem.title

                oldItem is SettingsItem.Switch && newItem is SettingsItem.Switch ->
                    oldItem.title == newItem.title

                oldItem is SettingsItem.SingleChoice && newItem is SettingsItem.SingleChoice ->
                    oldItem.title == newItem.title

                oldItem is SettingsItem.Slider && newItem is SettingsItem.Slider ->
                    oldItem.title == newItem.title

                oldItem is SettingsItem.Info && newItem is SettingsItem.Info ->
                    oldItem.title == newItem.title

                oldItem is SettingsItem.Action && newItem is SettingsItem.Action ->
                    oldItem.title == newItem.title

                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: SettingsItem, newItem: SettingsItem): Boolean {
            return oldItem == newItem
        }
    }
}