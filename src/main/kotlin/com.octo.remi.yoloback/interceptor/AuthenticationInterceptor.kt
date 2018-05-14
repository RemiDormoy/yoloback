package com.octo.remi.yoloback.interceptor

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationInterceptor : HandlerInterceptorAdapter(){

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.getHeader("token") != "yolo" && request.method == "POST") throw NotLoggedException()
        return super.preHandle(request, response, handler)
    }
}

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "On t'aime pas, t'es pas loggé... ¯\\_(ツ)_/¯")
class NotLoggedException : RuntimeException()