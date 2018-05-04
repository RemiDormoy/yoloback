package com.octo.remi.yoloback.interactor

import com.octo.remi.yoloback.entities.FootballPlayer
import com.octo.remi.yoloback.repository.SainteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SainteYoloInteractor {

    @Autowired
    private lateinit var repository: SainteRepository

    fun getSaintePlayers(): List<FootballPlayer> = repository.getGreenPlayers()

}