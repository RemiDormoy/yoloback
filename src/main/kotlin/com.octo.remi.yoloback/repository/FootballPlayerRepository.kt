package com.octo.remi.yoloback.repository

import com.octo.remi.yoloback.entities.FootballPlayerViewModel
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class FootballPlayerRepository {

    private val players = mutableListOf<FootballPlayerViewModel>()

    fun getPlayers() = players

    @Throws(PlayerAlreadyExistException::class)
    fun addPlayer(player: FootballPlayerViewModel) {
        if (players.contains(player)) {
            throw PlayerAlreadyExistException()
        } else {
            players.add(player)
        }
    }

    @PostConstruct
    fun init() {
        players.addAll(listOf(
                FootballPlayerViewModel("Cristiano", "Ronaldo", 33, "Madrid", "Portugal"),
                FootballPlayerViewModel("Neymar Jr", "da Silva Santos",26 , "Paris", "Brésil"),
                FootballPlayerViewModel("Antoine", "Griezmann", 27, "Madrid", "France")

        ))
    }

    class PlayerAlreadyExistException() : Throwable()
}