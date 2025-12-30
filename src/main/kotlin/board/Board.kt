package com.example.board

import jakarta.persistence.*

@Entity
@Table(name = "board")
open class Board(

    @field:Column(nullable = false)
    open var title: String,

    @field:Column(nullable = false)
    open var content: String
) {

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    protected constructor() : this("", "")
}