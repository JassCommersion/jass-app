package com.example.jass_app.data.service.impl

import android.util.Log
import com.example.jass_app.data.model.request.LoginRequest
import com.example.jass_app.data.model.request.RegistrationRequest
import com.example.jass_app.data.service.HttpClientService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.json.Json

class HttpClientServiceImpl(
    private val client: HttpClient
) : HttpClientService {

    //    Auth
    override suspend fun authTestConnection(): HttpResponse =
        client.get("$BASE_URL_AUTH/connection") {
        }
    override suspend fun register(request: RegistrationRequest): HttpResponse =
        client.post("$BASE_URL_AUTH/registration") {
            setBody(request)
            Log.d("REGISTRATION_BODY", body.toString())
            contentType(ContentType.Application.Json)
        }

    override suspend fun login(request: LoginRequest): HttpResponse =
        client.post(BASE_URL_AUTH) {
            setBody(request)
            Log.d("LOGIN_BODY", body.toString())
            contentType(ContentType.Application.Json)
        }

    override suspend fun confirmRegister(email: String, token: String): HttpResponse =
        client.post("$BASE_URL_AUTH/confirm") {
            url {
                parameters.append("email", email)
                parameters.append("token", token)
            }
        }

//    Profile

    override suspend fun getUserProfile(accessToken: String): HttpResponse =
        client.get("$BASE_URL_PROFILE/get/my_profile") {
            url {
                bearerAuth(accessToken)
                contentType(ContentType.Application.Json)
            }
        }


    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/api/v1"
        private const val BASE_URL_AUTH = "$BASE_URL/auth"
        private const val BASE_URL_PROFILE = "$BASE_URL/profile"
    }
}