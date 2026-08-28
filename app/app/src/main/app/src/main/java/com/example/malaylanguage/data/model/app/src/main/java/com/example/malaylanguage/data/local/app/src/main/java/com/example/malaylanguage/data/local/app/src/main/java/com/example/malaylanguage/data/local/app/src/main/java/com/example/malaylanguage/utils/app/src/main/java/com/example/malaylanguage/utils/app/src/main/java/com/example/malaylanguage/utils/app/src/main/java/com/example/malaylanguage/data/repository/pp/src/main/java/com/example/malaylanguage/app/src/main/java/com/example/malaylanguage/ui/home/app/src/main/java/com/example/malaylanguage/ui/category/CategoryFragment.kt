package com.example.malaylanguage.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malaylanguage.data.repository.AppRepository
import com.example.malaylanguage.databinding.FragmentCategoryBinding
import com.example.malaylanguage.ui.adapter.PhraseAdapter
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: AppRepository
    private lateinit var phraseAdapter: PhraseAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = AppRepository(requireContext())
        val categoryId = arguments?.getString("category_id") ?: ""

        phraseAdapter = PhraseAdapter({ item ->
            val intent = android.content.Intent(requireContext(), com.example.malaylanguage.ui.detail.DetailActivity::class.java)
            intent.putExtra("item_id", item.id)
            startActivity(intent)
        }, { item ->
            lifecycleScope.launch { repository.toggleFavorite(item) }
        })

        binding.rvCategoryItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategoryItems.adapter = phraseAdapter

        lifecycleScope.launch {
            repository.loadDatabase()
            val filteredItems = repository.allItems.filter { it.category_id == categoryId }
            phraseAdapter.submitList(filteredItems)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
