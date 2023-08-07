package io.github.rsromanowski.model

import io.github.rsromanowski.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Consumer(
    @Serializable(with = UUIDSerializer::class) val id: UUID,

    val custom_id: String? = null,
    val tags: Set<Tag> = emptySet(),
    val username: String? = null,

    val created_at: Int, // @Serializable(with = InstantSerializer::class) val created_at: Instant,
    val updated_at: Int, // @Serializable(with = InstantSerializer::class) val updated_at: Instant,
)

@Serializable
data class CreateConsumer(
    val username : String? = null,
    val customId: String? = null,
    val tags: Set<Tag> = emptySet(),
) {
    init {
        require(username != null || customId != null) { "Must specify at least one of username or customId"}
    }
}