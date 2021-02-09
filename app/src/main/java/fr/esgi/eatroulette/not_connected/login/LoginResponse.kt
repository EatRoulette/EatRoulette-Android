package fr.esgi.eatroulette.not_connected.login

import java.io.Serializable

data class LoginResponse(val token: String?, val message: String?) : Serializable