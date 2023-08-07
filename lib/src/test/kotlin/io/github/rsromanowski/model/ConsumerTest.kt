package io.github.rsromanowski.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ConsumerTest {
    @Test
    fun create() {
        assertThrows<IllegalArgumentException> { CreateConsumer() }
        assertThrows<IllegalArgumentException> { CreateConsumer(tags = setOf(Tag("tag"))) }
    }
}