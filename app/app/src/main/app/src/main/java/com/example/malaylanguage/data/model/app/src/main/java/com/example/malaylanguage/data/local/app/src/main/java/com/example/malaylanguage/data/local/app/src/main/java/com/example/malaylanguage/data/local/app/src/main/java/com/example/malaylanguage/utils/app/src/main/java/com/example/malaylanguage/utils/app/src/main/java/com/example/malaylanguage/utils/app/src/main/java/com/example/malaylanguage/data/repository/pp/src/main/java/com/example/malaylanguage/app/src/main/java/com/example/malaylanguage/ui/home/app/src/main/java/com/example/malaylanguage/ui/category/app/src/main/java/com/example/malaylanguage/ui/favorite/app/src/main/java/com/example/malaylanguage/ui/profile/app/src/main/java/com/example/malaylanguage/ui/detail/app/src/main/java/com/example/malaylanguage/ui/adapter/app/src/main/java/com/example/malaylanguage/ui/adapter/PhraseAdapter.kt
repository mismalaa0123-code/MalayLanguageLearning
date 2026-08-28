package com.example.malaylanguage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.malaylanguage.data.model.LanguageItem
import com.example.malaylanguage.databinding.ItemPhraseBinding

class PhraseAdapter(
    private val onItemClick: (LanguageItem) -> Unit,
    private val onFavoriteClick: (LanguageItem) -> Unit
) : ListAdapter<LanguageItem, PhraseAdapter.PhraseViewHolder>(DiffCallback) {

    inner class PhraseViewHolder(val binding: ItemPhraseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        val binding = ItemPhraseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhraseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhraseViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvBangla.text = item.bangla
        holder.binding.tvMalay.text = item.malay
        holder.binding.tvPronunciation.text = item.pronunciation_bn

        holder.binding.btnFavorite.text = if (item.isFavorite) "❤️" else "🤍"
        
        holder.binding.root.setOnClickListener { onItemClick(item) }
        holder.binding.btnFavorite.setOnClickListener { onFavoriteClick(item) }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<LanguageItem>() {
        override fun areItemsTheSame(oldItem: LanguageItem, newItem: LanguageItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: LanguageItem, newItem: LanguageItem): Boolean = oldItem == newItem
    }
}
