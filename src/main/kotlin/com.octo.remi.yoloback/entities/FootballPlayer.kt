package com.octo.remi.yoloback.entities

import java.time.LocalDate

data class FootballPlayer(val firstName: String,
                          val lastName: String,
                          val birthDate: LocalDate,
                          val club: String,
                          val nation: String)