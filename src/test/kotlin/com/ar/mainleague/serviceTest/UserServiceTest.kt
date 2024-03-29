package com.ar.mainleague.serviceTest

import com.ar.mainleague.modelo.Formation
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.Position
import com.ar.mainleague.modelo.User
import com.ar.mainleague.service.exceptions.InvalidSubstitutionException
import com.ar.mainleague.service.PlayerService
import com.ar.mainleague.service.UserService
import com.ar.mainleague.service.exceptions.InvalidPickExecption
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
    private lateinit var player2 : Player
    private lateinit var player3 : Player
    private lateinit var player4 : Player
    private lateinit var player5 : Player
    private lateinit var player6 : Player
    private lateinit var player7 : Player
    private lateinit var player8 : Player
    private lateinit var player9 : Player
    private lateinit var player10 : Player
    private lateinit var player11 : Player
    private lateinit var player12 : Player



    @BeforeEach
    fun setUp(){
        player1 = playerService.inscribePlayer(Position.GOALKEEPER, 29, "Martin", "Perez")

        player2 = playerService.inscribePlayer(Position.DEFENSE, 34, "Luciano", "Aute")
        player3 = playerService.inscribePlayer(Position.DEFENSE, 31, "Gaspar", "Robles")
        player4 = playerService.inscribePlayer(Position.DEFENSE, 27, "Marcos", "Rivas")
        player6 = playerService.inscribePlayer(Position.DEFENSE, 24, "Ivan", "Yanos")

        player5 = playerService.inscribePlayer(Position.MIDFIELDER, 19, "Lucas", "Peralta")
        player10 = playerService.inscribePlayer(Position.MIDFIELDER, 24, "Gastón", "Dafora")
        player8 = playerService.inscribePlayer(Position.MIDFIELDER, 21, "Diego", "Fogra")
        player11 = playerService.inscribePlayer(Position.MIDFIELDER, 28, "Alan", "Sian")

        player9 = playerService.inscribePlayer(Position.FORWARD, 18, "Gabriel", "Lopez")
        player7 = playerService.inscribePlayer(Position.FORWARD, 35, "Nahuel", "Estevez")

        player12 = playerService.inscribePlayer(Position.GOALKEEPER, 22, "Kevin", "Gramuglia")

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
        service.createUser("johnrhcp", formation)

        Assertions.assertThrows(UniqueException::class.java){service.createUser("johnrhcp", formation)}
    }

    @Test
    fun userCanPickPlayers(){
        service.pickPlayer(manudoro.nickname, player1.id!!)
        val players = service.getPlayers(manudoro.nickname)
        Assertions.assertTrue(players.any { p -> p.relId == player1.id })
    }

    @Test
    fun userCannotPickMoreThanElevenPlayers(){
        service.pickPlayer(manudoro.nickname, player1.id!!)
        service.pickPlayer(manudoro.nickname, player2.id!!)
        service.pickPlayer(manudoro.nickname, player3.id!!)
        service.pickPlayer(manudoro.nickname, player4.id!!)
        service.pickPlayer(manudoro.nickname, player5.id!!)
        service.pickPlayer(manudoro.nickname, player6.id!!)
        service.pickPlayer(manudoro.nickname, player7.id!!)
        service.pickPlayer(manudoro.nickname, player8.id!!)
        service.pickPlayer(manudoro.nickname, player9.id!!)
        service.pickPlayer(manudoro.nickname, player10.id!!)
        service.pickPlayer(manudoro.nickname, player11.id!!)
        Assertions.assertThrows(InvalidPickExecption::class.java){service.pickPlayer(manudoro.nickname, player12.id!!)}
    }

    @Test
    fun gettingUserPlayers(){
        service.pickPlayer(manudoro.nickname, player1.id!!)
        service.pickPlayer(manudoro.nickname, player2.id!!)
        service.pickPlayer(manudoro.nickname, player3.id!!)
        service.pickPlayer(manudoro.nickname, player4.id!!)
        service.pickPlayer(manudoro.nickname, player5.id!!)
        val team = service.getPlayers(manudoro.nickname)
        Assertions.assertEquals(5, team.size)
    }

    @Test
    fun userCanSubstitutePlayer(){
        service.pickPlayer(manudoro.nickname, player2.id!!)
        service.substitutePlayer(manudoro.nickname, player2.id!!, player3.id!!)
        val manudoroTeam = service.getPlayers(manudoro.nickname)
        Assertions.assertTrue(manudoroTeam.any { p-> p.relId == player3.id })
        Assertions.assertFalse(manudoroTeam.any { p-> p.relId == player2.id })
    }

    @Test
    fun userCannotChangePlayersFromDifferentPosition(){
        service.pickPlayer(manudoro.nickname, player1.id!!)
        Assertions.assertThrows(InvalidSubstitutionException::class.java)
        {service.substitutePlayer(manudoro.nickname, player1.id!!, player2.id!!)}

    }

    @Test
    fun userCannotSubstitutePlayerOutOfTheTeam(){
        Assertions.assertThrows(InvalidSubstitutionException::class.java)
        {service.substitutePlayer(manudoro.nickname, player2.id!!, player3.id!!)}

    }

    @Test
    fun userCannotSubstituteTheSamePlayer(){
        service.pickPlayer(manudoro.nickname, player1.id!!)
        Assertions.assertThrows(InvalidSubstitutionException::class.java)
        {service.substitutePlayer(manudoro.nickname, player1.id!!, player1.id!!)}
    }
    @Test
    fun userCanChangeFormation(){
        val newFormation = Formation(4,5,1)
        service.changeFormation(manudoro.nickname, newFormation)
        val formation = service.getFormation(manudoro.nickname)
        Assertions.assertEquals(4, formation.defenders)
        Assertions.assertEquals(5, formation.midfielders)
        Assertions.assertEquals(1, formation.forwards)
    }

    @Test
    fun changingFormationResetUserTeam(){
        service.pickPlayer(manudoro.nickname, player1.id!!)
        service.pickPlayer(manudoro.nickname, player2.id!!)
        val oldTeam = service.getPlayers(manudoro.nickname)
        val newFormation = Formation(4,5,1)
        service.changeFormation(manudoro.nickname, newFormation)
        val team = service.getPlayers(manudoro.nickname)
        Assertions.assertEquals(2, oldTeam.size)
        Assertions.assertEquals(0, team.size)

    }

    @Test
    fun changingFormationResetUserBudget(){
        service.pickPlayer(manudoro.nickname, player1.id!!)
        service.pickPlayer(manudoro.nickname, player2.id!!)
        val olderUser = service.getUserByNickname(manudoro.nickname)
        val newFormation = Formation(4,5,1)
        service.changeFormation(manudoro.nickname, newFormation)
        val user = service.getUserByNickname(manudoro.nickname)
        Assertions.assertTrue(olderUser.budget < 700.0)
        Assertions.assertTrue(user.budget == 700.0)

    }

    @Test
    fun pickPlayerReduceUserBudget(){
        val oldBudget = manudoro.budget
        service.pickPlayer(manudoro.nickname, player1.id!!)
        val userBudget = service.getUserBudget(manudoro.nickname)
        Assertions.assertTrue(userBudget == oldBudget - player1.rating)
    }

    @Test
    fun substitutePlayerReduceUserBudget(){
        val oldBudget = manudoro.budget
        service.pickPlayer(manudoro.nickname, player1.id!!)
        val userBudget = service.getUserBudget(manudoro.nickname)
        Assertions.assertTrue(userBudget == oldBudget - player1.rating)
        service.substitutePlayer(manudoro.nickname, player1.id!!, player12.id!!)
        val newestBudget = service.getUserBudget(manudoro.nickname)
        Assertions.assertTrue(newestBudget == userBudget - player12.rating + player1.rating)

    }


    @AfterEach
    fun clearUp(){
        cleaner.cleanDB()
    }
}