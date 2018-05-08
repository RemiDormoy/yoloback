package com.octo.remi.yoloback.repository

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.octo.remi.yoloback.entities.ChargeSpot
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@Component
class SpotRepository1 {

    private val uri = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=fichier-consolide-des-bornes-de-recharge-pour-vehicules-electriques-irve&rows=9999"

    fun getSpots(): List<ChargeSpot> {
        val restTemplate = RestTemplate()
        val jsonString = restTemplate.getForObject(uri, String::class.java)
        val mapper = ObjectMapper().registerKotlinModule()
        val json = mapper.readValue(jsonString, JsonSpot1Response::class.java)
        return json.records.map {
            ChargeSpot(latitude = BigDecimal(it.fields.latitude),
                    longitude = BigDecimal(it.fields.longitude),
                    label = it.fields.nom_station?: "",
                    address = it.fields.adresse_station?: "")
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonSpot1Response(val records: List<JsonSpot1Record>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonSpot1Record(val fields: JsonSpot1Fields)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonSpot1Fields(val longitude: String,
                           val latitude: String,
                           val adresse_station: String?,
                           val nom_station: String?)