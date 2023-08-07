package io.github.rsromanowski.model

import io.github.rsromanowski.util.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Information(@Serializable(with = UUIDSerializer::class) @SerialName("node_id") val nodeId: UUID)