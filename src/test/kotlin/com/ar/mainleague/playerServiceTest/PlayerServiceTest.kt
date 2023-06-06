package com.ar.mainleague.playerServiceTest

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.service.PlayerService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.InvalidPropertiesFormatException

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerServiceTest {

    @Autowired private lateinit var service : PlayerService
    private lateinit var lopez : Player

    @BeforeEach
    fun setUp(){
        lopez = service.inscribePlayer("fw", 18, "Gabriel", "Lopez")
        service.inscribePlayer("fw", 25, "Gabriel", "Garcia")
        service.inscribePlayer("df", 34, "Luciano", "Aute")
        service.inscribePlayer("gk", 29, "Martin", "Perez")
        service.inscribePlayer("mf", 19, "Diego", "Fogra")
        service.inscribePlayer("mf", 24, "Alan", "Sian")
    }

    @Test
    fun sePersisteUnJugador(){
        lopez = service.getPlayer(lopez.id!!)
        Assertions.assertNotNull(lopez.id)
    }

    @Test
    fun ocurreUnErrorAlPersistirSinPosicionValida(){
        Assertions.assertThrows(InvalidPropertiesFormatException::class.java,{service.inscribePlayer("sad",30, "Peco", "Pieza")})
    }

    @AfterAll
    fun clear(){
        service.clear()
    }

}