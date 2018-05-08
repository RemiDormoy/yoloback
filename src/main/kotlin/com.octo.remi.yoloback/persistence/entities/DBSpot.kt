package com.octo.remi.yoloback.persistence.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class DBSpot {

    @Id
    @GeneratedValue
    var id: Long = 0
    lateinit var label: String
    lateinit var adress: String
    lateinit var longitude: String
    lateinit var latitude: String
}