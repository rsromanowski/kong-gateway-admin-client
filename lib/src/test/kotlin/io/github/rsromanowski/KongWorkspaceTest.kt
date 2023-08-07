package io.github.rsromanowski

import kotlin.test.Test
import kotlin.test.assertEquals

class KongWorkspaceTest {
    @Test
    fun noWorkspace() {
        val kong = Kong.create(KongConfig("http://localhost:8001"))
        assertEquals("http://localhost:8001", kong.baseUrl)
    }

    @Test
    fun withWorkspace() {
        val kong = Kong.create(KongConfig("http://localhost:8001", "workspace"))
        assertEquals("http://localhost:8001/workspace", kong.baseUrl)
    }
}