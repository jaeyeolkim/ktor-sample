package com.example

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object HibernateUtil {

    val sessionFactory: SessionFactory by lazy {
        buildSessionFactory()
    }

    private fun buildSessionFactory(): SessionFactory {
        return try {
            val configuration = Configuration()

            // hibernate.cfg.xml 사용 (classpath 기준)
            configuration.configure()

            // 엔티티 수동 등록 (중요)
            configuration.addAnnotatedClass(com.example.board.Board::class.java)

            configuration.buildSessionFactory()
        } catch (ex: Exception) {
            System.err.println("❌ SessionFactory 생성 실패")
            ex.printStackTrace()
            throw ExceptionInInitializerError(ex)
        }
    }

    /**
     * 트랜잭션 유틸
     */
    fun <T> tx(block: (Session) -> T): T {
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