package com.example.malaylanguage.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class TTSManager(context: Context) {
    private var textToSpeech: TextToSpeech? = null
    private var isReady = false
    private val context = context

    init {
        initializeTTS()
    }

    private fun initializeTTS() {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // Try Malaysian Malay
                val result = textToSpeech?.setLanguage(Locale("ms", "MY"))
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Fallback to Malay
                    textToSpeech?.setLanguage(Locale("ms"))
                }
                isReady = true
            }
        }
    }

    fun speak(text: String) {
        if (isReady && textToSpeech != null) {
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    fun stop() {
        textToSpeech?.stop()
    }

    fun setPitch(pitch: Float) {
        textToSpeech?.setPitch(pitch)
    }

    fun setSpeechRate(rate: Float) {
        textToSpeech?.setSpeechRate(rate)
    }

    fun isReady(): Boolean = isReady

    fun shutdown() {
        textToSpeech?.shutdown()
    }
}
