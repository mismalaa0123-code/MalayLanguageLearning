package com.example.malaylanguage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.malaylanguage.adapter.CategoryAdapter
import com.example.malaylanguage.database.DatabaseManager
import com.example.malaylanguage.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var databaseManager: DatabaseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        databaseManager = DatabaseManager.getInstance()
        setupUI()
        loadDatabase()
    }

    private fun setupUI() {
        binding.tvTitle.text = "মালয়েশিয়ান ভাষা শিক্ষা"
        binding.tvTagline.text = "বাংলা থেকে সহজে মালয় ভাষা শিখুন"

        categoryAdapter = CategoryAdapter(emptyList())
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoryAdapter
        }
    }

    private fun loadDatabase() {
        lifecycleScope.launch {
            val success = databaseManager.loadDatabase()
            if (success) {
                val totalItems = databaseManager.getTotalCount()
                binding.tvInfo.text = "মোট: $totalItems টি শব্দ/বাক্য"
            } else {
                Toast.makeText(requireContext(), "ডেটাবেস লোড ব্যর্থ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
