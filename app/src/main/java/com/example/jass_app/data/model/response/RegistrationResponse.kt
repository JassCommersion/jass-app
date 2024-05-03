package com.example.jass_app.data.model.response

import kotlinx.serialization.Serializable

class RegistrationResponse(
    val accessToken: String,
    val refreshToken: String
)