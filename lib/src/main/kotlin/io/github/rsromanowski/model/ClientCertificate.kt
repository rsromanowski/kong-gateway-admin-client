package io.github.rsromanowski.model

import io.github.rsromanowski.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ClientCertificate(
    @Serializable(with = UUIDSerializer::class) val id: UUID
)