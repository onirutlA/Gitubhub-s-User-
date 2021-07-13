package com.onirutla.githubsuser.util

import androidx.recyclerview.widget.DiffUtil
import com.onirutla.githubsuser.data.source.remote.response.UserResponse

object Constant {
    const val BASE_URL = "https://api.github.com/"
    val diffUtil = object : DiffUtil.ItemCallback<UserResponse>() {
        override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
            return oldItem.id == newItem.id
        }
    }
}