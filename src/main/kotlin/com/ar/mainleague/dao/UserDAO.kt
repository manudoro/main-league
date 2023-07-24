package com.ar.mainleague.dao

import com.ar.mainleague.modelo.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserDAO : JpaRepository<User, Long> {

}