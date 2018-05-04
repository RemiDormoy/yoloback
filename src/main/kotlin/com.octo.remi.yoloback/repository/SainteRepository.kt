package com.octo.remi.yoloback.repository

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.octo.remi.yoloback.entities.FootballPlayer
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

@Component
class SainteRepository {
    fun getGreenPlayers(): List<FootballPlayer> {
        val uri = "http://api.football-data.org/v1/teams/527/players"

        val restTemplate = RestTemplate()
        val jsonString = restTemplate.getForObject(uri, String::class.java)
        val mapper =  ObjectMapper().registerKotlinModule()
        val json = mapper.readValue(jsonString, JsonSaintePlayer::class.java)
        return json.players.map {
            FootballPlayer(
                    firstName = it.name.substringBefore(" "),
                    lastName = it.name.substringAfter(" "),
                    birthDate = LocalDate.parse(it.dateOfBirth),
                    nation = it.nationality,
                    club = "ASSE"
            )
        }

    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaintePlayer(val name: String,
                        val nationality: String,
                        val dateOfBirth: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonSaintePlayer(val players: List<SaintePlayer>)