package io.github.rsromanowski

import io.github.rsromanowski.model.Certificate
import io.github.rsromanowski.model.Consumer
import io.github.rsromanowski.model.CreateConsumer
import io.github.rsromanowski.model.CreateService
import io.github.rsromanowski.model.Information
import io.github.rsromanowski.model.KongTag
import io.github.rsromanowski.model.Service
import io.github.rsromanowski.model.Status
import io.github.rsromanowski.model.Tag
import java.util.UUID

interface Kong {
    companion object{
        fun create(kongConfig : KongConfig) : Kong {
            return KongImpl(kongConfig)
        }
    }

    val baseUrl: String

    suspend fun information(): Information
    suspend fun status(): Status
    suspend fun endpoints(): List<String>
    suspend fun services(): List<Service>
    suspend fun certificates(): List<Certificate>
    suspend fun consumers(): List<Consumer>

    suspend fun createService(request: CreateService): Service
    suspend fun createService(request: CreateService, certificate: String): Service

    suspend fun createConsumer(request: CreateConsumer): Consumer
    suspend fun deleteConsumer(id: UUID)

    suspend fun tags(): List<KongTag>
    suspend fun tags(tag: Tag): List<KongTag>
}

