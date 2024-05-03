package com.example.jass_app.data.service

import com.example.jass_app.data.model.request.LoginRequest
import com.example.jass_app.data.model.request.RegistrationRequest
import io.ktor.client.statement.HttpResponse

interface HttpClientService {

//    Auth
    suspend fun authTestConnection(): HttpResponse
    suspend fun login(request: LoginRequest): HttpResponse
    suspend fun register(request: RegistrationRequest): HttpResponse
    suspend fun confirmRegister(email: String, token: String): HttpResponse

    suspend fun refreshAuth(refreshToken: String): HttpResponse

//    Profile

    suspend fun getUserProfile(accessToken: String): HttpResponse

    suspend fun getAllProfiles(accessToken: String): HttpResponse

    suspend fun getProfilesByIds(accessToken: String, ids: List<String>): HttpResponse

    suspend fun getProfileById(accessToken: String, id: String): HttpResponse


}