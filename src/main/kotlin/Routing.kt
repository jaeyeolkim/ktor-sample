package com.example

import com.example.board.boardRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/health") {
            call.respondText("OK")
        }
        get("/user/{id}") {
            // Ktor는 Non-blocking 모델이라 Blocking IO를 분리하지 않으면 전체 성능이 급격히 저하
            val id = withContext(Dispatchers.IO) {
                call.parameters["id"]!!.toInt()
            }
            call.respondText { "User $id" }
        }

        // 게시판
        boardRoutes()
    }
}
