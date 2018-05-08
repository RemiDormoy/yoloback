package com.octo.remi.yoloback.restcontroller

import com.octo.remi.yoloback.entities.ChargeSpotViewModel
import com.octo.remi.yoloback.presenter.SpotPresenter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ChargeSpotController {

    @Autowired
    private lateinit var presenter: SpotPresenter

    @GetMapping("/bornes")
    fun spot(@RequestParam("lat") latitude: String?,
             @RequestParam("long") longitude: String?): List<ChargeSpotViewModel> {
        return if (latitude != null && longitude != null) {
            presenter.getSpotsWithFilter(latitude, longitude)
        } else {
            presenter.getSpots()
        }
    }
}