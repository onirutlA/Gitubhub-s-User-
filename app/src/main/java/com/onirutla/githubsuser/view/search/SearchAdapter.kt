package com.onirutla.githubsuser.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onirutla.githubsuser.data.source.remote.response.UserResponse
import com.onirutla.githubsuser.databinding.UserItemBinding
import com.onirutla.githubsuser.util.GlideApp

class SearchAdapter : ListAdapter<UserResponse, SearchAdapter.UserViewHolder>(diffUtil) {
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
    ): SearchAdapter.UserViewHolder {
        binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.UserViewHolder, position: Int) {
        val userResponse = getItem(position)
        holder.bind(user = userResponse)
    }

    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserResponse) {
            binding.apply {
                userItemName.text = user.username
                userItemType.text = user.type
                GlideApp.with(userImage.context)
                    .load(user.avatarUrl)
                    .into(userImage)
            }
        }
    }
}