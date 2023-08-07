package io.github.rsromanowski.model

import kotlinx.serialization.Serializable

@Serializable
data class PaginatedResponse<T>(
    val data: List<T> = emptyList(),
    val next: String? = null,
    val offset: String? = null,
)
