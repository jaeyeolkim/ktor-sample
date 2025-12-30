package com.example.board

import jakarta.persistence.*

@Entity
@Table(name = "board")
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String,
    var content: String
)