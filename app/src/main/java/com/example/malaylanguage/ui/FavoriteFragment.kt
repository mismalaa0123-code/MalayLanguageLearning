package com.example.malaylanguage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.malaylanguage.adapter.FavoriteAdapter
import com.example.malaylanguage.data.local.FavoriteManager
import com.example.malaylanguage.databinding.FragmentFavoriteBinding
import com.example.malaylanguage.database.DatabaseManager
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favoriteManager: FavoriteManager
    private lateinit var databaseManager: DatabaseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        databaseManager = DatabaseManager.getInstance()
        favoriteManager = FavoriteManager(requireContext())
        setupUI()
        loadFavorites()
    }

    private fun setupUI() {
        favoriteAdapter = FavoriteAdapter(emptyList())
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }
    }

    private fun loadFavorites() {
        lifecycleScope.launch {
            val favorites = favoriteManager.getFavorites()
            val favoriteItems = databaseManager.getAllItems().filter { 
                favorites.contains(it.id.toString()) 
            }
            
            favoriteAdapter.updateData(favoriteItems)
            
            if (favoriteItems.isEmpty()) {
                binding.tvNoFavorites.visibility = View.VISIBLE
                binding.rvFavorites.visibility = View.GONE
            } else {
                binding.tvNoFavorites.visibility = View.GONE
                binding.rvFavorites.visibility = View.VISIBLE
            }
        }
    }
}
