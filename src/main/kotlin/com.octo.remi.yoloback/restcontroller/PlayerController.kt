package com.octo.remi.yoloback.restcontroller

import com.octo.remi.yoloback.entities.FootballPlayerViewModel
import com.octo.remi.yoloback.repository.FootballPlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class PlayerController {

    @Autowired
    private lateinit var playerRepository: FootballPlayerRepository

    @RequestMapping("/footballeurs", method = [(RequestMethod.GET)])
    fun players() = playerRepository.getPlayers()

    @RequestMapping("/footballeurs", method = [(RequestMethod.POST)])
    fun addPlayer(@RequestBody player: FootballPlayerViewModel) {
        try {
            playerRepository.addPlayer(player)
        } catch (e: FootballPlayerRepository.PlayerAlreadyExistException) {
            throw DuplicatePlayerException()
        }
    }

}

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Ce joueur existe déjà, déso pas déso ¯\\_(ツ)_/¯")
class DuplicatePlayerException() : RuntimeException()