package com.ar.mainleague.controllers

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
annotation class ServiceREST
