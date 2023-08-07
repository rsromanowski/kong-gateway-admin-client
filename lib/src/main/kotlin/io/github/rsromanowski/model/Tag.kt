package io.github.rsromanowski.model

import io.github.rsromanowski.util.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@JvmInline
@Serializable
value class Tag(val value: String)

@Serializable
data class KongTag(
    @SerialName("entity_name") val entityName: String,
    @Serializable(with = UUIDSerializer::class) @SerialName("entity_id") val entityId: UUID,
    val tag: Tag,
)