package com.octo.remi.yoloback

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}

@RestController
@SpringBootApplication
open class App {

    @RequestMapping("/yolo")
    fun home(): String {
        return "Vas y vas mourir yolo !"
    }

}