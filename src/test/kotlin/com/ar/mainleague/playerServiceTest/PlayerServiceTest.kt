package com.ar.mainleague.playerServiceTest

import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.Position
import com.ar.mainleague.modelo.utils.exceptions.BlankFieldException
import com.ar.mainleague.modelo.utils.exceptions.InvalidAgeException
import com.ar.mainleague.service.PlayerService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerServiceTest {

    @Autowired private lateinit var service : PlayerService
    private lateinit var lopez : Player

    @BeforeEach
    fun setUp(){
        lopez = service.inscribePlayer(Position.FORWARD, 18, "Gabriel", "Lopez")
        service.inscribePlayer(Position.FORWARD, 25, "Gabriel", "Garcia")
        service.inscribePlayer(Position.DEFENSE, 34, "Luciano", "Aute")
        service.inscribePlayer(Position.GOALKEEPER, 29, "Martin", "Perez")
        service.inscribePlayer(Position.MIDFIELDER, 19, "Diego", "Fogra")
        service.inscribePlayer(Position.MIDFIELDER, 24, "Alan", "Sian")
    }

    @Test
    fun seInscribeUnJugador(){
        lopez = service.getPlayer(lopez.id!!)
        Assertions.assertNotNull(lopez.id)
    }

    @Test
    fun seIntentaInscribirUnJugadorMenorDe18(){
        Assertions.assertThrows(InvalidAgeException::class.java
        ) { service.inscribePlayer(Position.MIDFIELDER, 12, "Alan", "Sian") }
    }

    @Test
    fun seIntentaInscribirUnJugadorMayorDe45(){
        Assertions.assertThrows(InvalidAgeException::class.java
        ) { service.inscribePlayer(Position.MIDFIELDER, 70, "Alan", "Sian") }
    }

    @Test
    fun seIntentaInscribirUnJugadorConNombreEnBlanco(){
        Assertions.assertThrows(BlankFieldException::class.java
        ) { service.inscribePlayer(Position.MIDFIELDER, 20, "", "Gomez") }
    }

    @Test
    fun seIntentaInscribirUnJugadorConApellidoEnBlanco(){
        Assertions.assertThrows(BlankFieldException::class.java
        ) { service.inscribePlayer(Position.MIDFIELDER, 20, "Pedro", "") }
    }


    @AfterAll
    fun clear(){
        service.clear()
    }

}