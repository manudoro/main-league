package com.ar.mainleague.controllers

import com.ar.mainleague.controllers.dto.FilterDTO
import com.ar.mainleague.controllers.dto.PlayerDTO
import com.ar.mainleague.modelo.PlayerSearchFilter
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
            val player = playerService.inscribePlayer(dto.position, dto.age, dto.name, dto.lastName)
            ResponseEntity.ok("The player ${player.name} ${player.lastName} inscribed successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when inscribing player: ${e.message}")
        }
    }

    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id : Long?) =
        PlayerDTO.desdeModelo(playerService.getPlayerStats(id!!))

    @GetMapping("/search")
    fun searchPlayers(@RequestBody dto : FilterDTO) : List<PlayerDTO>{
        val filter = PlayerSearchFilter(
            dto.name, dto.lastName, dto.fromAge, dto.toAge, dto.position, dto.fromRate, dto.toRate)
        val players = playerService.researchPlayers(filter)
        return players.map{p -> PlayerDTO.desdeModelo(p) }
    }

    @GetMapping("/all")
    fun getAll() : List<PlayerDTO>{
        val playersStats = playerService.getAll()
        return playersStats.map{p -> PlayerDTO.desdeModelo(p)}
    }



}


