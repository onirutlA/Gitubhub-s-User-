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
import androidx.navigation.findNavController
import com.onirutla.githubsuser.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            searchBar.setOnEditorActionListener { _, actionId, _ ->
                var search = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchUser()
                    requireView().closeSoftKeyboard(requireContext())
                    search = true
                }
                search
            }

            searchToolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }
    }

    private fun searchUser() {
        binding.apply {
            if (searchBar.text.isNotBlank()) {
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
        viewModel.getUserSearch(keyword).observe(viewLifecycleOwner, { response ->
            searchAdapter.submitList(response.items)
            binding.searchList.apply {
                adapter = searchAdapter
                setHasFixedSize(true)
            }
        })
    }

    private fun View.closeSoftKeyboard(context: Context) {
        val inputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
        this.clearFocus()
    }
}