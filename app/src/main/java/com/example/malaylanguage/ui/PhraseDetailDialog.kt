package com.example.malaylanguage.ui

import android.app.AlertDialog
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.malaylanguage.data.model.LanguageItem
import com.example.malaylanguage.databinding.DialogPhraseDetailBinding
import com.example.malaylanguage.utils.TTSManager

class PhraseDetailDialog : DialogFragment() {
    private lateinit var binding: DialogPhraseDetailBinding
    private lateinit var ttsManager: TTSManager
    private var item: LanguageItem? = null
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPhraseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        ttsManager = TTSManager(requireContext())
        item = arguments?.getSerializable("item") as? LanguageItem
        
        displayItem()
        setupListeners()
    }

    private fun displayItem() {
        item?.let { item ->
            binding.tvBangla.text = item.bangla
            binding.tvMalay.text = item.malay
            binding.tvPronunciation.text = item.pronunciationBn
            binding.tvMeaning.text = item.meaning
            binding.tvDifficulty.text = "স্তর: ${item.difficulty}"
        }
    }

    private fun setupListeners() {
        binding.btnSpeak.setOnClickListener {
            item?.let { ttsManager.speak(it.malay) }
        }

        binding.btnFavorite.setOnClickListener {
            isFavorite = !isFavorite
            updateFavoriteButton()
        }

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun updateFavoriteButton() {
        binding.btnFavorite.text = if (isFavorite) "❤️ প্রিয়" else "🤍 প্রিয়"
    }

    override fun onDestroy() {
        ttsManager.shutdown()
        super.onDestroy()
    }

    companion object {
        fun newInstance(item: LanguageItem): PhraseDetailDialog {
            return PhraseDetailDialog().apply {
                arguments = Bundle().apply {
                    putSerializable("item", item)
                }
            }
        }
    }
}
