package com.ar.mainleague

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.net.http.HttpResponse

@ServiceREST
@RequestMapping("/")
class MainControllerRest {

    @GetMapping
    fun index(): ResponseEntity<String>{
        return ResponseEntity.ok("Bienvenido a Main League")
    }
}