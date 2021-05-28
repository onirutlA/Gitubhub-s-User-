package com.onirutla.githubsuser.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true, )
data class SearchResponse(

    @Json(name = "total_count") val totalCount: Int?,
    @Json(name = "incomplete_results") val incompleteResults: Boolean?,
    @Json(name = "items") val items: List<UserResponse>?

)
