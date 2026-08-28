package com.example.malaylanguage.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malaylanguage.adapter.SearchResultAdapter
import com.example.malaylanguage.database.DatabaseManager
import com.example.malaylanguage.database.SearchManager
import com.example.malaylanguage.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchResultAdapter: SearchResultAdapter
    private lateinit var databaseManager: DatabaseManager
    private lateinit var searchManager: SearchManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        databaseManager = DatabaseManager.getInstance()
        setupUI()
        setupSearchListener()
        loadDatabase()
    }

    private fun setupUI() {
        binding.etSearch.hint = "বাংলায় লিখুন, যেমন: ভাত খাবো, পানি লাগবে"
        
        searchResultAdapter = SearchResultAdapter(emptyList())
        binding.rvResults.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchResultAdapter
        }
    }

    private fun setupSearchListener() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString()?.trim() ?: ""
                if (query.isNotEmpty()) {
                    performSearch(query)
                } else {
                    searchResultAdapter.updateData(emptyList())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun performSearch(query: String) {
        lifecycleScope.launch {
            val results = searchManager.search(query)
            searchResultAdapter.updateData(results)
            
            if (results.isEmpty()) {
                binding.tvNoResults.visibility = View.VISIBLE
            } else {
                binding.tvNoResults.visibility = View.GONE
            }
        }
    }

    private fun loadDatabase() {
        lifecycleScope.launch {
            val success = databaseManager.loadDatabase()
            if (success) {
                searchManager = SearchManager(databaseManager.getAllItems())
            } else {
                Toast.makeText(requireContext(), "ডেটাবেস লোড ব্যর্থ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
