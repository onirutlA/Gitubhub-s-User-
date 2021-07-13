package com.onirutla.githubsuser.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onirutla.githubsuser.databinding.FragmentDetailBinding
import com.onirutla.githubsuser.util.GlideApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClick()
        lifecycleScope.launchWhenStarted {
            setupDetail()
        }
    }

    private suspend fun setupDetail() {
        val username = args.username
        if (username != null)
            viewModel.getUserDetail(username).collect {
                binding.apply {
                    detailTvNavName.text = it.username
                    detailTvUserName.text = it.name
                    detailTvUserNickname.text = it.username
                    detailTvNumberRepository.text = "${it.publicRepos}"
                    detailTvNumberFollowing.text = "${it.following}"
                    detailTvNumberFollower.text = "${it.followers}"
                    detailTvTwitter.text = it.twitterUsername
                    detailTvLink.text = it.blog
                    detailTvCompany.text = it.company
                    detailTvLocation.text = it.location
                    GlideApp.with(detailUserImage)
                        .load(it.avatarUrl)
                        .into(detailUserImage)
                }
            }
    }

    private fun setupOnClick(){
        binding.apply {
            containerFollower.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToFollowerFragment(args.username))
            }
            containerFollowing.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToFollowingFragment(args.username))
            }
            detailToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}