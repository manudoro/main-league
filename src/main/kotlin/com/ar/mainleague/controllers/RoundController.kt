package com.ar.mainleague.controllers

import com.ar.mainleague.controllers.dto.RoundDTO
import com.ar.mainleague.controllers.dto.RoundUserDTO
import com.ar.mainleague.controllers.dto.TableDTO
import com.ar.mainleague.service.RoundService
import org.springframework.web.bind.annotation.*


@CrossOrigin
@ServiceREST
@RequestMapping("/round")
class RoundController(private val roundService : RoundService) {

    @PostMapping
    fun playRound(@RequestBody dto : RoundDTO) : RoundDTO{
        val round = roundService.playRound(dto.scores)
        return RoundDTO.desdeModelo(round)

    }

    @GetMapping("/winner/{roundNo}")
    fun roundWinner(@PathVariable roundNo : Long) : RoundUserDTO{
        return RoundUserDTO.desdeModelo(roundService.getRoundWinner(roundNo))
    }

    @GetMapping("/table/{roundNo}")
    fun roundTable(@PathVariable roundNo : Long) : TableDTO {
        val table = roundService.getRoundTable(roundNo)
        return TableDTO.desdeModelo(table)

    }





}