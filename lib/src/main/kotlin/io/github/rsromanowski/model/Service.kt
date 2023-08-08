package io.github.rsromanowski.model

import io.github.rsromanowski.util.UUIDSerializer
import kotlinx.serialization.SerialName
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
) {
    enum class Protocol(val value: String) {
        GRPC("grpc"),
        GRPCS("grpcs"),
        HTTP("http"),
        HTTPS("https"),
        TCP("tcp"),
        TLS("tls"),
        TLS_PASSTHROUGH("tls_passthrough"),
        UDP("udp"),
        WS("ws"),
        WSS("wss");

        companion object { fun of(value : String) = Protocol.values().first { it.value == value } }
    }
}

@Serializable
data class CreateService(
    val name: String?,
    val retries: Int?,
    val protocol: Service.Protocol,

    val host: String,
    val port: String,
    val path: String?,

    @SerialName("connect_timeout") val connectTimeout: Int?,
    @SerialName("write_timeout") val writeTimeout: Int?,
    @SerialName("read_timeout") val readTimeout: Int?,

    val tags: Set<Tag> = emptySet(),
    @SerialName("client_certificate") val clientCertificate: ClientCertificate,

    @SerialName("tls_verify") val tlsVerify: Boolean? = null,
    @SerialName("tls_verify_depth") val tlsVerifyDepth: Int? = null,
    @SerialName("ca_certificates") val caCertificates: List<@Serializable(with = UUIDSerializer::class) UUID>? = null,
    val enabled: Boolean = true,
)