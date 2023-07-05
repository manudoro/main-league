package com.ar.mainleague.playerServiceTest

import com.ar.mainleague.modelo.Position
import com.ar.mainleague.service.PlayerService
import com.ar.mainleague.service.TeamService
import com.ar.mainleague.service.impl.PlayerSearchFilter
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamServiceTest {
    @Autowired private lateinit var service: TeamService
    @Autowired private lateinit var playerService : PlayerService

    @BeforeEach
    fun setUp(){
        playerService.inscribePlayer(Position.FORWARD, 18, "Gabriel", "Lopez")
        playerService.inscribePlayer(Position.FORWARD, 25, "Gabriel", "Garcia")
        playerService.inscribePlayer(Position.DEFENSE, 34, "Luciano", "Aute")
        playerService.inscribePlayer(Position.GOALKEEPER, 29, "Martin", "Perez")
        playerService.inscribePlayer(Position.MIDFIELDER, 19, "Diego", "Fogra")
        playerService.inscribePlayer(Position.MIDFIELDER, 24, "Alan", "Sian")
    }

    @Test
    fun seBuscaJugadorPorApellido(){
        val searchFilter = PlayerSearchFilter(lastName = "Fogra")
        val listWithFogra = service.researchPlayers(searchFilter)
        Assertions.assertTrue(listWithFogra.all{p -> p.lastName == "Fogra"})
        Assertions.assertEquals(1, listWithFogra.size)
    }

    @AfterAll
    fun clear(){
        playerService.clear()
    }


}