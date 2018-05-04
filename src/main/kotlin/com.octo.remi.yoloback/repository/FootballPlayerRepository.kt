package com.octo.remi.yoloback.repository

import com.octo.remi.yoloback.entities.FootballPlayer
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class FootballPlayerRepository {

    private val players = mutableListOf<FootballPlayer>()

    fun getPlayers() = players

    @PostConstruct
    fun init() {
        players.addAll(listOf(
                FootballPlayer("Cristiano", "Ronaldo", 33, "Madrid", "Portugal")
        ))
    }
}