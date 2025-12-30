package com.example

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence

object HibernateUtil {
    private val sessionFactory = org.hibernate.cfg.Configuration()
        .configure() // hibernate.cfg.xml
        .addAnnotatedClass(com.example.board.Board::class.java)
        .buildSessionFactory()

    fun <T> tx(block: (org.hibernate.Session) -> T): T {
        val session = sessionFactory.openSession()
        val tx = session.beginTransaction()
        return try {
            val result = block(session)
            tx.commit()
            result
        } catch (e: Exception) {
            tx.rollback()
            throw e
        } finally {
            session.close()
        }
    }
}