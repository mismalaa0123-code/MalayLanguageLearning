package com.example.malaylanguage.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malaylanguage.MainActivity
import com.example.malaylanguage.R
import com.example.malaylanguage.data.model.Category
import com.example.malaylanguage.data.model.LanguageItem
import com.example.malaylanguage.data.repository.AppRepository
import com.example.malaylanguage.databinding.FragmentHomeBinding
import com.example.malaylanguage.ui.adapter.CategoryAdapter
import com.example.malaylanguage.ui.adapter.PhraseAdapter
import com.example.malaylanguage.utils.SearchManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: AppRepository
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var phraseAdapter: PhraseAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = AppRepository(requireContext())
        
        setupCategoryRecyclerView()
        setupSearchRecyclerView()

        lifecycleScope.launch {
            repository.loadDatabase()
            setupDailyConversation()
        }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isEmpty()) {
                    binding.rvSearchResults.visibility = View.GONE
                    binding.rvCategories.visibility = View.VISIBLE
                } else {
                    performSearch(query)
                }
            }
        })
    }

    private fun setupCategoryRecyclerView() {
        val categories = listOf(
            Category("daily_conversation", "🗣️ দৈনন্দিন কথা"),
            Category("hospital", "🏥 হাসপাতাল"),
            Category("shopping", "🛒 বাজার"),
            Category("workplace", "💼 কাজ"),
            Category("food_drink", "🍚 খাবার"),
            Category("transport", "🚕 যাতায়াত"),
            Category("bank", "🏦 ব্যাংক"),
            Category("emergency", "🚨 জরুরি"),
            Category("immigration", "🛂 Immigration"),
            Category("mobile_internet", "📱 মোবাইল")
        )
        categoryAdapter = CategoryAdapter(categories) { categoryId ->
            val bundle = Bundle().apply { putString("category_id", categoryId) }
            findNavController().navigate(R.id.action_home_to_category, bundle)
        }
        binding.rvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategories.adapter = categoryAdapter
    }

    private fun setupSearchRecyclerView() {
        phraseAdapter = PhraseAdapter({ item ->
            val intent = android.content.Intent(requireContext(), com.example.malaylanguage.ui.detail.DetailActivity::class.java)
            intent.putExtra("item_id", item.id)
            startActivity(intent)
        }, { item ->
            lifecycleScope.launch { repository.toggleFavorite(item) }
        })
        binding.rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchResults.adapter = phraseAdapter
    }

    private fun setupDailyConversation() {
        val dailyItems = repository.allItems.filter { it.category_id == "daily_conversation" }.take(5)
        // Could setup a horizontal scroll here if needed
    }

    private fun performSearch(query: String) {
        lifecycleScope.launch {
            val results = withContext(Dispatchers.Default) {
                SearchManager.search(repository.allItems, query)
            }
            if (results.isEmpty()) {
                binding.tvEmptyState.visibility = View.VISIBLE
                binding.rvSearchResults.visibility = View.GONE
            } else {
                binding.tvEmptyState.visibility = View.GONE
                binding.rvSearchResults.visibility = View.VISIBLE
                binding.rvCategories.visibility = View.GONE
                phraseAdapter.submitList(results)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
