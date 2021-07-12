package com.onirutla.githubsuser.view.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.onirutla.githubsuser.databinding.FragmentFollowingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowingViewModel by viewModels()
    private val followingAdapter: FollowingAdapter by lazy { FollowingAdapter() }
    private val args: FollowingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClick()
        val username = args.username
        lifecycleScope.launchWhenStarted {
            if (username != null) {
                viewModel.getUserFollowing(username).collect {
                    followingAdapter.submitList(it)
                    binding.rvFollowing.apply {
                        setHasFixedSize(true)
                        adapter = followingAdapter
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupOnClick() {
        binding.followingToolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }
}