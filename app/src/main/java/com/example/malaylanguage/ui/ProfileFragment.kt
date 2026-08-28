package com.example.malaylanguage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.malaylanguage.data.local.PreferencesManager
import com.example.malaylanguage.databinding.FragmentProfileBinding
import com.example.malaylanguage.database.DatabaseManager
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var preferencesManager: PreferencesManager
    private lateinit var databaseManager: DatabaseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        preferencesManager = PreferencesManager(requireContext())
        databaseManager = DatabaseManager.getInstance()
        setupUI()
        loadProfileData()
    }

    private fun setupUI() {
        binding.tvAppName.text = "মালয়েশিয়ান ভাষা শিক্ষা"
        binding.tvVersion.text = "সংস্করণ 1.0.0"
        
        binding.btnDarkMode.setOnClickListener {
            toggleDarkMode()
        }
        
        binding.btnAbout.setOnClickListener {
            showAbout()
        }
    }

    private fun loadProfileData() {
        lifecycleScope.launch {
            val totalLearned = preferencesManager.getTotalLearned()
            val totalVocabulary = databaseManager.getTotalCount()
            
            binding.tvTotalVocabulary.text = "মোট শব্দ: $totalVocabulary"
            binding.tvLearned.text = "শেখা: $totalLearned"
        }
    }

    private fun toggleDarkMode() {
        lifecycleScope.launch {
            val currentMode = preferencesManager.getDarkMode()
            val newMode = when (currentMode) {
                "light" -> "dark"
                "dark" -> "light"
                else -> "light"
            }
            preferencesManager.setDarkMode(newMode)
            
            val nightMode = when (newMode) {
                "dark" -> AppCompatDelegate.MODE_NIGHT_YES
                "light" -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
            AppCompatDelegate.setDefaultNightMode(nightMode)
        }
    }

    private fun showAbout() {
        val aboutText = """মালয়েশিয়ান ভাষা শিক্ষা
            |বাংলা থেকে সহজে মালয় ভাষা শিখুন
            |সংস্করণ 1.0.0
            |নির্মাতা: Arafat islam""".trimMargin()
        
        binding.tvAboutContent.text = aboutText
        binding.tvAboutContent.visibility = View.VISIBLE
    }
}
