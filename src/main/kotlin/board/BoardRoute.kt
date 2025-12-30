package com.example.board

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.boardRoutes() {
    val repository = BoardRepository()

    route("/boards") {
        // 목록
        get {
            call.respond(repository.findAll())
        }

        // 상세
        get("/{id}") {
            val id = call.parameters["id"]!!.toLong()
            val board = repository.findById(id)
                ?: return@get call.respond(HttpStatusCode.NotFound)

            call.respond(board)
        }

        // 등록
        post {
            val req = call.receive<BoardCreateReq>()
            val board = repository.save(
                Board(title = req.title, content = req.content)
            )
            call.respond(HttpStatusCode.Created, board)
        }
    }
}

data class BoardCreateReq(
    val title: String,
    val content: String
)