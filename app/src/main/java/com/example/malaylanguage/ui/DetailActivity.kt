package com.example.malaylanguage.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.example.malaylanguage.data.model.LanguageItem
import com.example.malaylanguage.databinding.ActivityDetailBinding
import com.example.malaylanguage.utils.TTSManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var ttsManager: TTSManager
    private var isFavorite = false
    private var currentItem: LanguageItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ttsManager = TTSManager(this)
        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "বিস্তারিত"
    }

    private fun setupListeners() {
        binding.btnSpeak.setOnClickListener {
            currentItem?.let { item ->
                ttsManager.speak(item.malay)
            }
        }

        binding.btnFavorite.setOnClickListener {
            isFavorite = !isFavorite
            updateFavoriteButton()
        }
    }

    private fun updateFavoriteButton() {
        binding.btnFavorite.text = if (isFavorite) "❤️ প্রিয়" else "🤍 প্রিয়"
    }

    override fun onSupportNavigateUp(): Boolean {
        return onBackPressedDispatcher.onBackPressed().let { true }
    }

    override fun onDestroy() {
        ttsManager.shutdown()
        super.onDestroy()
    }
}
