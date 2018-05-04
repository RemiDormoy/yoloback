package com.octo.remi.yoloback.presenter

import com.octo.remi.yoloback.entities.FootballPlayerViewModel
import com.octo.remi.yoloback.interactor.SainteYoloInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period

@Component
class SainteYoloPresenter @Autowired constructor(private val interactor: SainteYoloInteractor) {
    fun onSainteListRequested(): List<FootballPlayerViewModel> {
        val players = interactor.getSaintePlayers()
        return players.map {
            FootballPlayerViewModel(
                    firstName = it.firstName,
                    lastName = it.lastName,
                    nation = it.nation,
                    club = it.club,
                    age = getAge(it.birthDate)
            )
        }
    }

    private fun getAge(date: LocalDate): Int {
        val now = LocalDate.now()
        return Period.between(date, now).years
    }
}