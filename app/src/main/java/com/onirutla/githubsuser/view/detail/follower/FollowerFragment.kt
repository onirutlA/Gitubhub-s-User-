package com.onirutla.githubsuser.view.detail.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.onirutla.githubsuser.databinding.FragmentFollowerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowerFragment(private val username: String) : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowerViewModel by viewModels()
    private val followerAdapter: FollowerAdapter by lazy { FollowerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.getUserFollowers(username).collect {
                followerAdapter.submitList(it)
                binding.rvFollower.apply {
                    setHasFixedSize(true)
                    adapter = followerAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}