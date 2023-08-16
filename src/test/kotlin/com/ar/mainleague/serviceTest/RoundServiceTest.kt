package com.ar.mainleague.serviceTest

import com.ar.mainleague.modelo.Round
import com.ar.mainleague.service.RoundService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoundServiceTest {

    @Autowired lateinit var roundService : RoundService


    lateinit var round1 : MutableMap<Long, Int>
    @BeforeEach
    fun setUp(){
        round1 = mutableMapOf()
        round1[1] = 12
        round1[2] = 15
        round1[3] = 16
        round1[4] = 12
        round1[5] = 15
        round1[6] = 16
        round1[7] = 12
        round1[8] = 15
        round1[9] = 16
        round1[10] = 12
        round1[11] = 15
        round1[12] = 11
        round1[13] = 12
        round1[14] = 15
        round1[15] = 16
        round1[16] = 12
        round1[17] = 15
        round1[18] = 16
        round1[19] = 12
        round1[20] = 15
        round1[21] = 16
        round1[22] = 12
        round1[23] = 19
        round1[24] = 11

    }

    @Test
    fun persistARound(){

        roundService.playRound(round1)
        roundService.playRound(round1)
        roundService.playRound(round1)


    }

}