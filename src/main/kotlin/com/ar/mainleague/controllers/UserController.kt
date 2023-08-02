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

    @GetMapping("/{nickname}")
    fun getUser(@PathVariable nickname : String) : UserDTO {
        val user = userService.getUserByNickname(nickname)
        val players = userService.getPlayers(nickname)
        return UserDTO.desdeModelo(user, players)
    }


    @GetMapping("{nickname}/team")
    fun getTeam(@PathVariable nickname : String) : List<PlayerDTO> {
        val team = userService.getPlayers(nickname)
        return team.map{p -> PlayerDTO.desdeModelo(p)}
    }

    @PutMapping("{nickname}/pick/{playerId}")
    fun pickPlayer(@PathVariable nickname : String, @PathVariable playerId : Long) : ResponseEntity<String> {
        return try {
            userService.pickPlayer(nickname, playerId)
            ResponseEntity.ok("The player $playerId picked successfully by user $nickname")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when picking player: ${e.message}")
        }
    }

    @PutMapping("{nickname}/formation")
    fun changeFormation(@PathVariable nickname : String, @RequestBody dto : FormationDTO) : ResponseEntity<String> {
        return try {
            userService.changeFormation(nickname, dto.aModelo())
            ResponseEntity.ok("The user $nickname changed formation succesfully")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when changing formation: ${e.message}")
        }
    }

    @GetMapping("{nickname}/formation")
    fun getFormation(@PathVariable nickname: String) : FormationDTO {
        val formation = userService.getFormation(nickname)
        return FormationDTO.desdeModelo(formation)
    }

    @PutMapping("/{nickname}/substitute/{playerOutId}/{playerInId}")
    fun substitutePlayer(
        @PathVariable nickname : String,
        @PathVariable playerOutId : Long,
        @PathVariable playerInId : Long
    ) : ResponseEntity<String> {
        return try {
            userService.substitutePlayer(nickname, playerOutId, playerInId)
            ResponseEntity.ok("The player $playerOutId replaced successfully by ${playerInId} in user $nickname team")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred when replacing player: ${e.message}")
        }
    }




}