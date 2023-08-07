package io.github.rsromanowski

import io.github.rsromanowski.model.CreateConsumer
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(Lifecycle.PER_CLASS)
class KongTest {
    private val localhost = "http://localhost:8001"

    @BeforeAll fun setup() {
        val script = javaClass.classLoader.resources("kong-docker.sh").findFirst().map { it.file }.orElseThrow()

        ProcessBuilder()
            .command(script)
            .start()
            .waitFor(1L, TimeUnit.MINUTES)
    }
    @AfterAll fun cleanup() {
        ProcessBuilder()
            .command(javaClass.classLoader.resources("kong-docker-cleanup.sh").findFirst().map { it.file }.orElseThrow())
//            .start()
//            .waitFor(10L, TimeUnit.SECONDS)
    }

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

class KongWorkspaceTest {
    @Test fun noWorkspace() {
        val kong = Kong.create(KongConfig("http://localhost:8001"))
        assertEquals("http://localhost:8001", kong.baseUrl)
    }

    @Test fun withWorkspace() {
        val kong = Kong.create(KongConfig("http://localhost:8001", "workspace"))
        assertEquals("http://localhost:8001/workspace", kong.baseUrl)
    }
}