package io.github.rsromanowski.model

import io.github.rsromanowski.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Certificate(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val cert: String,
    val cert_alt: String,
    val key: String,
    val key_alt: String,

    val tags: Set<Tag> = emptySet(),

    val created_at: Int, // @Serializable(with = InstantSerializer::class) val created_at: Instant,
    val updated_at: Int, // @Serializable(with = InstantSerializer::class) val updated_at: Instant,
)

