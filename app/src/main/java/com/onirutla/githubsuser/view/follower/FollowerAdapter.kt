package com.onirutla.githubsuser.view.follower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.githubsuser.data.source.remote.response.UserResponse
import com.onirutla.githubsuser.databinding.UserItemBinding
import com.onirutla.githubsuser.util.Constant.diffUtil
import com.onirutla.githubsuser.util.GlideApp

class FollowerAdapter : ListAdapter<UserResponse, FollowerAdapter.FollowerViewHolder>(diffUtil) {
    private lateinit var binding: UserItemBinding

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
            }
        }
    }
}