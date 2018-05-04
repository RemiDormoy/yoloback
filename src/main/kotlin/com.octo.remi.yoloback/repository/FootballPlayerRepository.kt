package com.octo.remi.yoloback.repository

import com.octo.remi.yoloback.entities.FootballPlayer
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class FootballPlayerRepository {

    private val players = mutableListOf<FootballPlayer>()

    fun getPlayers() = players

    @Throws(PlayerAlreadyExistException::class)
    fun addPlayer(player: FootballPlayer) {
        if (players.contains(player)) {
            throw PlayerAlreadyExistException()
        } else {
            players.add(player)
        }
    }

    @PostConstruct
    fun init() {
        players.addAll(listOf(
                FootballPlayer("Cristiano", "Ronaldo", 33, "Madrid", "Portugal"),
                FootballPlayer("Neymar Jr", "da Silva Santos",26 , "Paris", "Brésil"),
                FootballPlayer("Antoine", "Griezmann", 27, "Madrid", "France")

        ))
    }

    class PlayerAlreadyExistException() : Throwable()
}