package com.example.board
import com.example.module
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class BoardRouteTest {

    @Test
    fun `POST boards 성공`() = testApplication {
        application {
            module() // Application.kt의 module()
        }

        val response = client.post("/boards") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                  "title": "hello",
                  "content": "world"
                }
                """.trimIndent()
            )
        }

        assertEquals(HttpStatusCode.Created, response.status)
    }
}