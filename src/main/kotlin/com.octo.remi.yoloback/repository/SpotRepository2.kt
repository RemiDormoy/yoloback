package com.octo.remi.yoloback.repository

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.octo.remi.yoloback.entities.ChargeSpot
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@Component
class SpotRepository2 {

    private val uri = "http://www.opendata56.fr/dataserver/morbihan-energies/data/iza2kc85?&\$format=json&\$top=1000"

    fun getSpots() : List<ChargeSpot> {
        val restTemplate = RestTemplate()
        val jsonString = restTemplate.getForObject(uri, String::class.java)
        val mapper = ObjectMapper().registerKotlinModule()
        val json = mapper.readValue(jsonString, JsonSpot2Response::class.java)
        return json.d.results.map {
            ChargeSpot(
                    latitude = BigDecimal(it.Ylatitude),
                    longitude = BigDecimal(it.Xlongitude),
                    label = it.n_station,
                    address = it.ad_station)
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonSpot2Response(val d: JsonSpot2d)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonSpot2d(val results: List<JsonSpot2Results>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonSpot2Results(val Xlongitude: String,
                            val Ylatitude: String,
                            val ad_station: String,
                            val n_station: String)