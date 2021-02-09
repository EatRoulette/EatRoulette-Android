package fr.esgi.eatroulette.not_connected.register

data class Register(val lastName: String, val firstName: String
                    , val town: String, val address: String, val postalCode: String
                    , val phone: String, val email: String
                    , val password: String, val type: String)
