package com.onirutla.githubsuser.view.detail.follower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.githubsuser.data.source.remote.response.UserResponse
import com.onirutla.githubsuser.databinding.UserItemBinding
import com.onirutla.githubsuser.util.GlideApp
import com.onirutla.githubsuser.view.detail.DetailFragmentDirections

class FollowerAdapter : ListAdapter<UserResponse, FollowerAdapter.FollowerViewHolder>(diffUtil) {
    private lateinit var binding: UserItemBinding

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<UserResponse>() {
            override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerViewHolder {
        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val userResponse = getItem(position)
        holder.bind(user = userResponse)
    }

    inner class FollowerViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserResponse) {
            val username = user.username
            val type = user.type
            binding.apply {
                userItemName.text = username
                userItemType.text = type
                GlideApp.with(userItemImage.context)
                    .load(user.avatarUrl)
                    .into(userItemImage)
                userItemContainer.setOnClickListener {
                    val direction = DetailFragmentDirections.actionDetailFragmentSelf(username)
                    it.findNavController().navigate(direction)
                }
            }
        }
    }
}