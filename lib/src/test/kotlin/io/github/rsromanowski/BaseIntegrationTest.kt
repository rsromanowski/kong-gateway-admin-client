package io.github.rsromanowski

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import java.util.concurrent.TimeUnit

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseIntegrationTest {
    @BeforeAll
    fun setup() {
        val script = javaClass.classLoader.resources("kong-docker.sh").findFirst().map { it.file }.orElseThrow()

        ProcessBuilder()
            .command(script)
            .start()
            .waitFor(1L, TimeUnit.MINUTES)
    }

    //@AfterAll
    fun cleanup() {
        val script = javaClass.classLoader.resources("kong-docker-cleanup.sh").findFirst()
            .map { it.file }.orElseThrow()

        ProcessBuilder()
            .command(script)
            .start()
            .waitFor(10L, TimeUnit.SECONDS)
    }
}