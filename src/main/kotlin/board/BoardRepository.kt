package com.example.board

import com.example.HibernateUtil

class BoardRepository {

    fun findAll(): List<Board> =
        HibernateUtil.tx { em ->
            em.createQuery(
                "select b from Board b order by b.id desc",
                Board::class.java
            ).resultList
        }

    fun findById(id: Long): Board? =
        HibernateUtil.tx { em ->
            em.find(Board::class.java, id)
        }

    fun save(board: Board): Board =
        HibernateUtil.tx { em ->
            em.persist(board)
            board
        }
}