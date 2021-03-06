package com.octo.remi.yoloback.restcontroller

import com.octo.remi.yoloback.presenter.SainteYoloPresenter
import com.octo.remi.yoloback.repository.SainteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SainteYoloListController {

    @Autowired
    private lateinit var presenter: SainteYoloPresenter

    @GetMapping("/sainte")
    fun sainte() = presenter.onSainteListRequested()
}