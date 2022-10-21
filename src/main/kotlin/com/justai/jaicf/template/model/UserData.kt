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
    val lastAnswered: Int = 0,

    @Column(name = "score")
    val score: Int = 0
)