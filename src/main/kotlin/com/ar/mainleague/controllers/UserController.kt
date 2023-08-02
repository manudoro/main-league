package com.ar.mainleague.controllers
import com.ar.mainleague.controllers.dto.FormationDTO
import com.ar.mainleague.controllers.dto.PlayerDTO
import com.ar.mainleague.controllers.dto.UserDTO
import com.ar.mainleague.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@ServiceREST
@RequestMapping("/user")
class UserController(private val userService : UserService) {

    @PostMapping
    fun createUser(@RequestBody dto : UserDTO): ResponseEntity<String> {
        return try {
            val formation = dto.formacion.aModelo()
            val user = userService.createUser(dto.nickname, formation)
            ResponseEntity.ok("The player ${user.nickname} signed up successfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when signing up user: ${e.message}")
        }
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id : Long) = UserDTO.desdeModelo(userService.getUser(id))

    @GetMapping("/{id}")
    fun getTeam(@PathVariable id : Long) : List<PlayerDTO> {
        val team = userService.getPlayers(id)
        return team.map{p -> PlayerDTO.desdeModelo(p)}
    }

    @PutMapping("/pick/{userId}/{playerId}")
    fun pickPlayer(@PathVariable userId : Long, @PathVariable playerId : Long) : ResponseEntity<String> {
        return try {
            userService.pickPlayer(userId, playerId)
            ResponseEntity.ok("The player $playerId picked successfully by user $userId")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when picking player: ${e.message}")
        }
    }

    @PutMapping("/chageFormation/{userId}")
    fun changeFormation(@PathVariable userId : Long, @RequestBody dto : FormationDTO) : ResponseEntity<String> {
        return try {
            userService.changeFormation(userId, dto.aModelo())
            ResponseEntity.ok("The user $userId changed formation succesfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when changing formation: ${e.message}")
        }
    }

    @GetMapping("/formation/{userId}")
    fun getFormation(@PathVariable userId: Long) : FormationDTO {
        val formation = userService.getFormation(userId)
        return FormationDTO.desdeModelo(formation)
    }

    @PutMapping("/pick/{userId}/{playerOutId}/{playerInId}")
    fun substitutePlayer(
        @PathVariable userId : Long,
        @PathVariable playerOutId : Long,
        @PathVariable playerInId : Long
    ) : ResponseEntity<String> {
        return try {
            userService.substitutePlayer(userId, playerOutId, playerInId)
            ResponseEntity.ok("The player $playerOutId replaced successfully by ${playerInId} in user $userId team")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when replacing player: ${e.message}")
        }
    }




}