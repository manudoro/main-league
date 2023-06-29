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
import java.util.InvalidPropertiesFormatException

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerServiceTest {

    @Autowired private lateinit var service : PlayerService
    private lateinit var lopez : Player

    @BeforeEach
    fun setUp(){
        lopez = service.inscribePlayer(Position.Forward, 18, "Gabriel", "Lopez")
        service.inscribePlayer(Position.Forward, 25, "Gabriel", "Garcia")
        service.inscribePlayer(Position.Defense, 34, "Luciano", "Aute")
        service.inscribePlayer(Position.Goalkeeper, 29, "Martin", "Perez")
        service.inscribePlayer(Position.Midfielder, 19, "Diego", "Fogra")
        service.inscribePlayer(Position.Midfielder, 24, "Alan", "Sian")
    }

    @Test
    fun seInscribeUnJugador(){
        lopez = service.getPlayer(lopez.id!!)
        Assertions.assertNotNull(lopez.id)
    }

    @Test
    fun seIntentaInscribirUnJugadorMenorDe18(){
        Assertions.assertThrows(InvalidAgeException::class.java
        ) { service.inscribePlayer(Position.Midfielder, 12, "Alan", "Sian") }
    }

    @Test
    fun seIntentaInscribirUnJugadorMayorDe45(){
        Assertions.assertThrows(InvalidAgeException::class.java
        ) { service.inscribePlayer(Position.Midfielder, 70, "Alan", "Sian") }
    }

    @Test
    fun seIntentaInscribirUnJugadorConNombreEnBlanco(){
        Assertions.assertThrows(BlankFieldException::class.java
        ) { service.inscribePlayer(Position.Midfielder, 20, "", "Gomez") }
    }

    @Test
    fun seIntentaInscribirUnJugadorConApellidoEnBlanco(){
        Assertions.assertThrows(BlankFieldException::class.java
        ) { service.inscribePlayer(Position.Midfielder, 20, "Pedro", "") }
    }


    @AfterAll
    fun clear(){
        service.clear()
    }

}