package com.ar.mainleague.serviceTest

import com.ar.mainleague.modelo.Formation
import com.ar.mainleague.modelo.Player
import com.ar.mainleague.modelo.Position
import com.ar.mainleague.modelo.User
import com.ar.mainleague.service.PlayerService
import com.ar.mainleague.service.UserService
import com.ar.mainleague.service.impl.Cleaner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataSet {
    @Autowired
    private lateinit var cleaner: Cleaner
    @Autowired
    private lateinit var service: UserService
    @Autowired
    private lateinit var playerService : PlayerService

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
    private lateinit var player13 : Player
    private lateinit var player14 : Player
    private lateinit var player15 : Player
    private lateinit var player16 : Player
    private lateinit var player17 : Player
    private lateinit var player18 : Player
    private lateinit var player19 : Player
    private lateinit var player20 : Player
    private lateinit var player21 : Player
    private lateinit var player22 : Player
    private lateinit var player23 : Player
    private lateinit var player24 : Player

    private lateinit var diegoPR : User
    private lateinit var ivan12 : User


    @Test
    fun setData() {
        player1  = playerService.inscribePlayer(Position.GOALKEEPER, 29, "Martin", "Perez",60.0)
        player2  = playerService.inscribePlayer(Position.DEFENSE, 34, "Luciano", "Aute",73.0)
        player3  = playerService.inscribePlayer(Position.DEFENSE, 31, "Gaspar", "Robles",55.0)
        player4  = playerService.inscribePlayer(Position.DEFENSE, 27, "Marcos", "Rivas",62.0)
        player5  = playerService.inscribePlayer(Position.DEFENSE, 24, "Ivan", "Yanos",13.0)
        player6  = playerService.inscribePlayer(Position.MIDFIELDER, 19, "Lucas", "Peralta",70.0)
        player7  = playerService.inscribePlayer(Position.MIDFIELDER, 24, "Gastón", "Dafora",67.0)
        player8  = playerService.inscribePlayer(Position.MIDFIELDER, 21, "Diego", "Fogra",52.0)
        player9  = playerService.inscribePlayer(Position.MIDFIELDER, 28, "Alan", "Sian",60.0)
        player10 = playerService.inscribePlayer(Position.FORWARD, 18, "Gabriel", "Lopez",56.0)
        player11 = playerService.inscribePlayer(Position.FORWARD, 35, "Nahuel", "Estevez",73.0)
        player12 = playerService.inscribePlayer(Position.GOALKEEPER, 22, "Kevin", "Gramuglia",64.0)

        player13 = playerService.inscribePlayer(Position.GOALKEEPER, 29, "Marcos", "Gramuglia ",65.0)
        player14 = playerService.inscribePlayer(Position.DEFENSE, 34, "Diego", "Yanos",63.0)
        player15 = playerService.inscribePlayer(Position.DEFENSE, 31, "Gastón ", "Fogra",85.0)
        player16 = playerService.inscribePlayer(Position.DEFENSE, 27, "Kevin", "Dafora",42.0)
        player17 = playerService.inscribePlayer(Position.DEFENSE, 24, "Gaspar ", "Estevez",63.0)
        player18 = playerService.inscribePlayer(Position.MIDFIELDER, 19, "Lucas", "Aute",40.0)
        player19 = playerService.inscribePlayer(Position.MIDFIELDER, 24, "Martin ", "Rivas",37.0)
        player20 = playerService.inscribePlayer(Position.MIDFIELDER, 21, "Luciano", "Fogra",82.0)
        player21 = playerService.inscribePlayer(Position.FORWARD, 28, "Ivan", "Peralta",70.0)
        player22 = playerService.inscribePlayer(Position.FORWARD, 18, "Alan", "Lopez",66.0)
        player23 = playerService.inscribePlayer(Position.FORWARD, 35, "Nahuel", "Fogra",73.0)
        player24 = playerService.inscribePlayer(Position.GOALKEEPER, 22, "Gabriel", "Sian",64.0)

        diegoPR = service.createUser("diegoPR", Formation(4,4,2))
        ivan12 = service.createUser("ivan12", Formation(4,3,3))

        service.pickPlayer(diegoPR.nickname, player1.id!!)
        service.pickPlayer(diegoPR.nickname, player2.id!!)
        service.pickPlayer(diegoPR.nickname, player3.id!!)
        service.pickPlayer(diegoPR.nickname, player4.id!!)
        service.pickPlayer(diegoPR.nickname, player5.id!!)
        service.pickPlayer(diegoPR.nickname, player6.id!!)
        service.pickPlayer(diegoPR.nickname, player7.id!!)
        service.pickPlayer(diegoPR.nickname, player8.id!!)
        service.pickPlayer(diegoPR.nickname, player9.id!!)
        service.pickPlayer(diegoPR.nickname, player10.id!!)
        service.pickPlayer(diegoPR.nickname, player11.id!!)

        service.pickPlayer(ivan12.nickname, player12.id!!)
        service.pickPlayer(ivan12.nickname, player14.id!!)
        service.pickPlayer(ivan12.nickname, player15.id!!)
        service.pickPlayer(ivan12.nickname, player16.id!!)
        service.pickPlayer(ivan12.nickname, player17.id!!)
        service.pickPlayer(ivan12.nickname, player18.id!!)
        service.pickPlayer(ivan12.nickname, player19.id!!)
        service.pickPlayer(ivan12.nickname, player20.id!!)
        service.pickPlayer(ivan12.nickname, player21.id!!)
        service.pickPlayer(ivan12.nickname, player22.id!!)
        service.pickPlayer(ivan12.nickname, player23.id!!)

    }



    @Test
    fun clean(){
        cleaner.cleanDB()
    }

}