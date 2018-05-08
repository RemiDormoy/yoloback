package com.octo.remi.yoloback.interactor

import com.octo.remi.yoloback.entities.ChargeSpot
import com.octo.remi.yoloback.persistence.entities.DBSpot
import com.octo.remi.yoloback.persistence.repository.DBSpotRepository
import com.octo.remi.yoloback.repository.SpotRepository1
import com.octo.remi.yoloback.repository.SpotRepository2
import com.octo.remi.yoloback.utils.addAllIf
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class SpotInteractor(private val db: DBSpotRepository,
                     private val firstRepository: SpotRepository1,
                     private val secondRepository: SpotRepository2) {

    fun getSpots(): List<ChargeSpot> {
        val dbPlayers = db.findAll()
        return if (dbPlayers.isEmpty()) {
            println("yo ! je suis en train de faire un appel réseau")
            val remoteList = getListFromNetwork()
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

    private fun getListFromNetwork(): List<ChargeSpot> {
        val list = mutableListOf<ChargeSpot>()
        list.addAll(firstRepository.getSpots())
        val elements = secondRepository.getSpots()
        //list.addAll(elements)
        list.addAllIf(secondRepository.getSpots(), { spot ->
            list.find {
                spot.longitude.setScale(10, RoundingMode.HALF_UP) == it.longitude.setScale(10, RoundingMode.HALF_UP) && spot.latitude.setScale(10, RoundingMode.HALF_UP) == it.latitude.setScale(10, RoundingMode.HALF_UP)
                //spot == it
            } == null
        })
        return list
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