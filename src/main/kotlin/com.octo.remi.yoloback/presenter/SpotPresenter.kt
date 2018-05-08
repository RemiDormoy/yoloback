package com.octo.remi.yoloback.presenter

import com.octo.remi.yoloback.entities.ChargeSpotViewModel
import com.octo.remi.yoloback.interactor.SpotInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class SpotPresenter @Autowired constructor(private val interactor: SpotInteractor) {
    fun getSpots(): List<ChargeSpotViewModel> {
        val spots = interactor.getSpots()
        return spots.map {
            ChargeSpotViewModel(
                    it.label,
                    it.latitude.toString(),
                    it.longitude.toString(),
                    it.address
            )
        }
    }

    fun getSpotsWithFilter(latitude: String, longitude: String): List<ChargeSpotViewModel> {
        val long = BigDecimal(longitude)
        val lat = BigDecimal(latitude)
        return interactor.getSpots().sortedBy {
            ((long - it.longitude).abs() + (lat - it.latitude).abs())
        }.take(10).map {
            ChargeSpotViewModel(
                    it.label,
                    it.latitude.toString(),
                    it.longitude.toString(),
                    it.address
            )
        }

    }
}