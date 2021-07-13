package com.onirutla.githubsuser.view.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.onirutla.githubsuser.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private val userAdapter: UserAdapter by lazy { UserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchbar()
        setupToolbar()
    }

    private fun setupSearchbar() {
        binding.searchBar.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener isSearchClicked(actionId)
        }
    }

    private fun isSearchClicked(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchUser()
            requireView().closeSoftKeyboard(requireContext())
            return true
        }
        return false
    }

    private fun setupToolbar() {
        binding.searchToolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun searchUser() {
        binding.apply {
            if (searchBar.text!!.isNotBlank()) {
                searchList.requestFocus()
                getUserSearch(searchBar.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUserSearch(keyword: String) {
        lifecycleScope.launchWhenStarted {
            viewModel.getUserSearch(username = keyword).collect {
                userAdapter.submitList(it.items)
                initDataToList()
            }
        }
    }

    private fun initDataToList() {
        binding.searchList.apply {
            adapter = userAdapter
            setHasFixedSize(true)
        }
    }

    private fun View.closeSoftKeyboard(context: Context) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
        this.clearFocus()
    }
}