package com.octo.remi.yoloback.interactor

import com.octo.remi.yoloback.entities.ChargeSpot
import com.octo.remi.yoloback.persistence.entities.DBSpot
import com.octo.remi.yoloback.persistence.repository.DBSpotRepository
import com.octo.remi.yoloback.repository.SpotRepository1
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class SpotInteractor(private val db: DBSpotRepository,
                     private val firstRepository: SpotRepository1) {
    fun getSpots(): List<ChargeSpot> {
        val dbPlayers = db.findAll()
        return if (dbPlayers.isEmpty()) {
            println("yo ! je suis en train de faire un appel réseau")
            val remoteList = firstRepository.getSpots()
            db.saveAll(remoteList.map { it.transformToDb() })
            remoteList
        } else {
            println("yo ! j'ai pas besoin de faire un appel réseau")
            dbPlayers.map {
                ChargeSpot(
                        address = it.adress,
                        label = it.label,
                        longitude = BigDecimal(it.longitude),
                        latitude = BigDecimal(it.latitude)
                )
            }
        }
    }
}

private fun ChargeSpot.transformToDb(): DBSpot {
    val db = DBSpot()
    kotlin.with(db) {
        adress = this@transformToDb.address
        label = this@transformToDb.label
        longitude = this@transformToDb.longitude.toString()
        latitude = this@transformToDb.latitude.toString()
    }
    return db
}