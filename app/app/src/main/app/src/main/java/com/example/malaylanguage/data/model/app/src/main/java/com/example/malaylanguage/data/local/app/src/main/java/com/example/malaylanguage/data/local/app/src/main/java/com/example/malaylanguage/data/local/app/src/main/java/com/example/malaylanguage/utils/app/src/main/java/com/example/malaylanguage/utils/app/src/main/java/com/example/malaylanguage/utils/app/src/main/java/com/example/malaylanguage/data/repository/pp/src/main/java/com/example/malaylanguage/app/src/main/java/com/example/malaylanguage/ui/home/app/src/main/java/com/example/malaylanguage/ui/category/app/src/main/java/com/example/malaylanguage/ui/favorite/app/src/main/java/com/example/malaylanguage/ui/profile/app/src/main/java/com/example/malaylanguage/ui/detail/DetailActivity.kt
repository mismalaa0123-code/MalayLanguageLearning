package com.example.malaylanguage.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.malaylanguage.data.repository.AppRepository
import com.example.malaylanguage.databinding.ActivityDetailBinding
import com.example.malaylanguage.utils.TTSManager
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var repository: AppRepository
    private lateinit var ttsManager: TTSManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = AppRepository(this)
        ttsManager = TTSManager(this)

        val itemId = intent.getIntExtra("item_id", -1)

        lifecycleScope.launch {
            repository.loadDatabase()
            val item = repository.allItems.find { it.id == itemId }
            item?.let {
                binding.tvBangla.text = it.bangla
                binding.tvMalay.text = it.malay
                binding.tvPronunciation.text = "উচ্চারণ: ${it.pronunciation_bn}"
                binding.tvMeaning.text = "অর্থ: ${it.meaning}"
                
                binding.tvExampleBn.text = "উদা: ${it.example.bangla}"
                binding.tvExampleMalay.text = it.example.malay

                binding.btnAudio.setOnClickListener { _ ->
                    if (it.audio.enabled) {
                        ttsManager.speak(it.malay)
                    }
                }

                binding.btnFavorite.setOnClickListener { _ ->
                    lifecycleScope.launch { repository.toggleFavorite(it) }
                }

                repository.markProgress(it.id)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsManager.shutdown()
    }
}
