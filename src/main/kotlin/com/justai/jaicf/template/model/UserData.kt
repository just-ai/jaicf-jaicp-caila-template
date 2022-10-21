package com.justai.jaicf.template.model

import javax.persistence.*

@Entity
class UserData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(nullable = false, name = "name")
    val name: String = "",

    @Column(name = "last_answered")
    var lastAnswered: Int = 0,

    @Column(name = "score")
    var score: Int = 0
)