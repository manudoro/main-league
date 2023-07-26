package com.ar.mainleague.serviceTest

import com.ar.mainleague.modelo.Formation
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.Position
import com.ar.mainleague.modelo.User
import com.ar.mainleague.service.PlayerService
import com.ar.mainleague.service.UserService
import com.ar.mainleague.service.exceptions.UniqueException
import com.ar.mainleague.service.impl.Cleaner
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Autowired private lateinit var cleaner: Cleaner
    @Autowired private lateinit var service: UserService
    @Autowired private lateinit var playerService : PlayerService
    private lateinit var formation : Formation
    private lateinit var manudoro : User
    private lateinit var player1 : Player




    @BeforeEach
    fun setUp(){
        player1 = playerService.inscribePlayer(Position.GOALKEEPER, 29, "Martin", "Perez")

        playerService.inscribePlayer(Position.DEFENSE, 34, "Luciano", "Aute")
        playerService.inscribePlayer(Position.DEFENSE, 31, "Gaspar", "Robles")
        playerService.inscribePlayer(Position.DEFENSE, 27, "Marcos", "Rivas")
        playerService.inscribePlayer(Position.DEFENSE, 24, "Ivan", "Yanos")

        playerService.inscribePlayer(Position.MIDFIELDER, 19, "Lucas", "Peralta")
        playerService.inscribePlayer(Position.MIDFIELDER, 24, "Gastón", "Dafora")
        playerService.inscribePlayer(Position.MIDFIELDER, 21, "Diego", "Fogra")
        playerService.inscribePlayer(Position.MIDFIELDER, 28, "Alan", "Sian")

        playerService.inscribePlayer(Position.FORWARD, 18, "Gabriel", "Lopez")
        playerService.inscribePlayer(Position.FORWARD, 35, "Nahuel", "Estevez")


        formation = Formation(4, 4, 2)

        manudoro = service.createUser("manudoro", formation)
    }

    @Test
    fun sePersisteUnUsuario(){
        val user = service.createUser("manunintendo", formation)
        val recoveredUser = service.getUser(user.id!!)
        Assertions.assertNotNull(recoveredUser.id)
    }

    @Test
    fun noEsPosiblePersistirUsuariosConElMismoNickname(){
        service.createUser("manudoro", formation)

        Assertions.assertThrows(UniqueException::class.java){service.createUser("manudoro", formation)}
    }

    @Test
    fun userCanPickPlayers(){
        service.pickPlayer(manudoro.id!!, player1.id!!)
        val players = service.getPlayers(manudoro.id!!)
        Assertions.assertTrue(players.any { p -> p.id == player1.id })
    }

    @Test
    fun userCanChangeFormation(){
        val newFormation = Formation(4,5,1)
        service.changeFormation(manudoro.id!!, newFormation)
        val formation = service.getFormation(manudoro.id!!)
        Assertions.assertEquals(4, formation.defenders)
        Assertions.assertEquals(5, formation.midfielders)
        Assertions.assertEquals(1, formation.forwards)
    }

    @AfterEach
    fun clearUp(){
        cleaner.cleanDB()
    }
}