package com.octo.remi.yoloback.interactor

import com.octo.remi.yoloback.entities.FootballPlayer
import com.octo.remi.yoloback.persistence.entities.DBFootballPlayer
import com.octo.remi.yoloback.persistence.repository.DBPlayerRepository
import com.octo.remi.yoloback.repository.SainteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class SainteYoloInteractor @Autowired constructor(private val repository: SainteRepository,
                                                  private val db: DBPlayerRepository) {

    fun getSaintePlayers(): List<FootballPlayer> {
        val dbPlayers = db.findAll()
        return if (dbPlayers.isEmpty()) {
            println("yo ! je suis en train de faire un appel réseau")
            db.saveAll(repository.getGreenPlayers().map { it.transformToDb() })
            repository.getGreenPlayers()
        } else {
            println("yo ! j'ai pas besoin de faire un appel réseau")
            dbPlayers.map {
                FootballPlayer(
                        firstName = it.firstName,
                        lastName = it.lastName,
                        birthDate = LocalDate.parse(it.birthDate),
                        club = it.club,
                        nation = it.nation
                )
            }
        }
    }

}

private fun FootballPlayer.transformToDb(): DBFootballPlayer {
    val db = DBFootballPlayer()
    with(db) {
        firstName = this@transformToDb.firstName
        lastName = this@transformToDb.lastName
        birthDate = this@transformToDb.birthDate.toString()
        club = this@transformToDb.club
        nation = this@transformToDb.nation
    }
    return db
}
