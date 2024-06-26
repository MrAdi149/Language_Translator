package com.aditya.nlocr.translation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.nlocr.R
import com.aditya.nlocr.databinding.ItemTranslationLanguageBinding
import com.aditya.nlocr.translation.util.SupportedLanguages

class TranslationLanguageAdapter(
    private val languages: List<SupportedLanguages>,
    private val onItemClick: (SupportedLanguages) -> Unit
) : RecyclerView.Adapter<TranslationLanguageAdapter.LanguageViewHolder>() {

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    class LanguageViewHolder(
        private val binding: ItemTranslationLanguageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(supportedLanguages: SupportedLanguages) {
            binding.language = supportedLanguages
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemTranslationLanguageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languages[position]
        holder.bind(language)

        // Set the background based on the selected item
        if (position == selectedItemPosition) {
            holder.itemView.setBackgroundResource(R.drawable.background_clicked_button)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.background_unclicked_button)
        }

        holder.itemView.setOnClickListener {
            // Update the selected item position
            val previousSelectedItemPosition = selectedItemPosition
            selectedItemPosition = holder.adapterPosition

            // Notify the adapter about the item change to update backgrounds
            notifyItemChanged(previousSelectedItemPosition)
            notifyItemChanged(selectedItemPosition)
            onItemClick.invoke(language)
        }
    }

    override fun getItemCount(): Int {
        return languages.size
    }
}