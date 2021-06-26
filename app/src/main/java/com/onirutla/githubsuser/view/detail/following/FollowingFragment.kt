package com.onirutla.githubsuser.view.detail.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.onirutla.githubsuser.databinding.FragmentFollowingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FollowingFragment(private val username: String) : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val followingAdapter: FollowingAdapter by lazy { FollowingAdapter() }
    private val viewModel: FollowingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.getUserFollowing(username).collect {
                followingAdapter.submitList(it)
                binding.rvFollowing.apply {
                    setHasFixedSize(true)
                    adapter = followingAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}