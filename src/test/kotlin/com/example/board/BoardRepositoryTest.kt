package com.example.board

import com.example.HibernateUtil
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardRepositoryTest {

    private val repository = BoardRepository()

    @Test
    fun `게시글 저장 시 ID가 생성된다`() {
        val board = Board(
            title = "test title",
            content = "test content"
        )

        repository.save(board)

        assertNotNull(board.id)
        assertTrue(board.id!! > 0)
    }

    @Test
    fun `게시글 조회`() {
        val saved = Board("title", "content")
        repository.save(saved)

        val found = HibernateUtil.tx { em ->
            em.find(Board::class.java, saved.id)
        }

        assertNotNull(found)
        assertEquals("title", found!!.title)
        assertEquals("content", found.content)
    }
}