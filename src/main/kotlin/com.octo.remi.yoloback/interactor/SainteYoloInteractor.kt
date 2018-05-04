package com.octo.remi.yoloback.interactor

import com.octo.remi.yoloback.entities.FootballPlayer
import com.octo.remi.yoloback.repository.SainteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class SainteYoloInteractor @Autowired constructor(private val repository: SainteRepository) {

    open fun getSaintePlayers(): List<FootballPlayer> = repository.getGreenPlayers()

}