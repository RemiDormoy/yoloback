package com.octo.remi.yoloback.persistence.entities

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class DBFootballPlayer {

    @Id
    @GeneratedValue
    var id: Long = 0
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var birthDate: String
    lateinit var club: String
    lateinit var nation: String
}