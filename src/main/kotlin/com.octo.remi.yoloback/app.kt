package com.octo.remi.yoloback

import com.octo.remi.yoloback.interceptor.AuthenticationInterceptor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

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

@Configuration
open class AppConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AuthenticationInterceptor())
    }
}