package io.github.rsromanowski

import io.github.rsromanowski.model.CreateConsumer
import kotlinx.coroutines.runBlocking
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

class KongTest : BaseIntegrationTest() {
    private val localhost = "http://localhost:8001"

    @Test fun validateClientCreation() {
        val kong = Kong.create(KongConfig(localhost))

        runBlocking { kong.information().also { println(it.nodeId)} }
        runBlocking { kong.status().also { println(it) } }
        runBlocking { kong.services().also { println(it) } }
        runBlocking { kong.certificates().also { println(it) } }
    }

    @Test fun createConsumer() {
        val kong = Kong.create(KongConfig(localhost))

        val username = UUID.randomUUID().toString()
        val consumer = runBlocking { kong.createConsumer(CreateConsumer(username = username)) }
        val consumers = runBlocking { kong.consumers() }

        assert(consumers.isNotEmpty()) { "Expected to find newly created consumer"}
        assert(consumers.any { it.id == consumer.id})

        runBlocking {  kong.deleteConsumer(consumer.id) }
        val newConsumers = runBlocking { kong.consumers() }
        assertEquals(consumers.size, newConsumers.size + 1, "Expected one less consumer")
    }
}

