package io.github.rsromanowski.model

import io.github.rsromanowski.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Service(
    @Serializable(with = UUIDSerializer::class) val id: UUID,

    val ca_certificates: List<@Serializable(with = UUIDSerializer::class) UUID>,
    val client_certificate: ClientCertificate,
    val connect_timeout: Int,
    val enabled: Boolean,
    val host: String,
    val name: String,
    val path: String,
    val port: Int,
    val protocol: String,
    val read_timeout: Int,
    val retries: Int,
    val tags: Set<Tag> = emptySet(),
    val tls_verify: Boolean,
    val tls_verify_depth: Int?,
    val write_timeout: Int,

    val created_at: Int, // @Serializable(with = InstantSerializer::class) val created_at: Instant,
    val updated_at: Int, // @Serializable(with = InstantSerializer::class) val updated_at: Instant,
)