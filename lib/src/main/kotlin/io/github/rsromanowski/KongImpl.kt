package io.github.rsromanowski

import io.github.rsromanowski.model.Certificate
import io.github.rsromanowski.model.Consumer
import io.github.rsromanowski.model.CreateConsumer
import io.github.rsromanowski.model.Endpoints
import io.github.rsromanowski.model.Information
import io.github.rsromanowski.model.PaginatedResponse
import io.github.rsromanowski.model.Service
import io.github.rsromanowski.model.Status
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.UUID

internal class KongImpl(kongConfig: KongConfig) : Kong {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                isLenient = true
            })
        }
    }

    override suspend fun information() : Information = get("/").body()
    override suspend fun status() : Status = get("/status").body()
    override suspend fun endpoints() : List<String> = get("/endpoints").body<Endpoints>().data
    override suspend fun services() : List<Service> = get("/services").body<PaginatedResponse<Service>>().data
    override suspend fun consumers() : List<Consumer> {
        return get("/consumers").also { println(it.status) }.body<PaginatedResponse<Consumer>>().data
    }

    override suspend fun createService() {
        TODO("Not yet implemented")
    }

    override suspend fun createConsumer(request : CreateConsumer): Consumer {
        return post("/consumers") {
            setBody(request)
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun deleteConsumer(id: UUID) {
        delete("/consumers/$id")
    }

    override suspend fun certificates() : List<Certificate> {
        return get("/certificates").body<PaginatedResponse<Certificate>>().data
    }

    override val baseUrl = "${kongConfig.address}${kongConfig.workspace?.let { "/$it" } ?: ""}"
    private suspend fun get(path: String) = client.get("$baseUrl$path")
    private suspend fun post(
        path : String,
        block : HttpRequestBuilder.() -> Unit,
    ) = client.post("$baseUrl$path", block).also { println("${it.status} ${it.bodyAsText()}") }
    private suspend fun delete(path: String) = client.delete("$baseUrl$path")
}