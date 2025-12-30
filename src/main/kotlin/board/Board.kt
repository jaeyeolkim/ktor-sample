package com.example.board

import jakarta.persistence.*

@Entity
@Table(name = "board")
@Access(AccessType.FIELD)
open class Board(

    @Column(nullable = false)
    open var title: String,

    @Column(nullable = false)
    open var content: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    // JPA 기본 생성자 (필수)
    protected constructor() : this("", "")
}