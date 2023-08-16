package com.ar.mainleague.controllers

import com.ar.mainleague.controllers.dto.PlayerDTO
import com.ar.mainleague.service.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@ServiceREST
@RequestMapping("/player")
class PlayerController(private val playerService : PlayerService) {

    @PostMapping
    fun inscribePlayer(@RequestBody dto : PlayerDTO): ResponseEntity<String> {
        return try {
            val player = playerService.inscribePlayer(dto.position, dto.age, dto.name, dto.lastName, dto.rating)
            ResponseEntity.ok("The player ${player.name} ${player.lastName} inscribed successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when inscribing player: ${e.message}")
        }
    }

    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id : Long) =
        PlayerDTO.desdeModelo(playerService.getPlayer(id))


    @GetMapping("/all")
    fun getAll() : List<PlayerDTO>{
        val playersStats = playerService.getAll()
        return playersStats.map{p -> PlayerDTO.desdeModelo(p)}
    }



}


