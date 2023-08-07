package io.github.rsromanowski.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Status(@SerialName("configuration_hash") val configurationHash: String? = null)