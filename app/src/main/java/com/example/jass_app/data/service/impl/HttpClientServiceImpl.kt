package com.example.jass_app.data.service.impl

import android.util.Log
import com.example.jass_app.data.model.request.LoginRequest
import com.example.jass_app.data.model.request.RegistrationRequest
import com.example.jass_app.data.service.HttpClientService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.cookie
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Cookie
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.date.GMTDate
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.json.Json
import java.time.Month

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
        client.post("$BASE_URL_AUTH/registration/confirm") {
            url {
                parameters.append("email", email)
                parameters.append("token", token)
            }
        }

    override suspend fun refreshAuth(refreshToken: String): HttpResponse =
        client.post("$BASE_URL_AUTH/confirm/refresh") {
            cookie(name = "refreshToken", value = refreshToken, expires = GMTDate(
                seconds = 0,
                minutes = 0,
                hours = 10,
                dayOfMonth = 3,
                month = io.ktor.util.date.Month.MAY,
                year = 2024
            )
            )
        }

//    TODO: UserRecovery + PasswordChange

//    Profile

    override suspend fun getUserProfile(accessToken: String): HttpResponse = // MutableList<ShortProfileResponse>
        client.get("$BASE_URL_PROFILE/get/my_profile") {
            url {
                bearerAuth(accessToken)
            }
        }

    override suspend fun getAllProfiles(accessToken: String): HttpResponse = // MutableList<ShortProfileResponse>
        client.get("$BASE_URL_PROFILE/get/all_profiles") {
            url {
                bearerAuth(accessToken)
            }
        }

    override suspend fun getProfilesByIds(accessToken: String, ids: List<String>): HttpResponse = // MutableList<ShortProfileResponse>
        client.get("$BASE_URL_PROFILE/get/profiles_by_ids"){
            url {
                bearerAuth(accessToken)
                ids.forEach{
                    parameters.append("ids", it)
                }
            }
        }

    override suspend fun getProfileById(accessToken: String, id: String): HttpResponse = // FullProfileResponse
        client.get("$BASE_URL_PROFILE/get/profile_by_id"){
            url {
                bearerAuth(accessToken)
                parameters.append("id", id)
            }
        }
//    Profile.Change

    override suspend fun changeLoginAndPersonalInfo(
        accessToken: String,
        userName: String?,
        firstName: String?,
        lastName: String?,
        gender_name: String?,
        birth_date: String?,
        residenceCountry: String?
    ): HttpResponse =
        client.patch("$BASE_URL_PROFILE/change/change_personal_info") {
            url {
                bearerAuth(accessToken)
                if (userName != null)parameters.append("userName", userName)
                if (firstName != null)parameters.append("firstName", firstName)
                if (lastName != null)parameters.append("lastName", lastName)
                if (gender_name != null)parameters.append("gender_name", gender_name)
                if (birth_date != null)parameters.append("birth_date", birth_date)
                if (residenceCountry != null)parameters.append("residenceCountry", residenceCountry)
            }

        }

    override suspend fun changeProfileSettings(
        accessToken: String,
        profileVisibility: String?,
        language: String?,
        colorTheme: String?
    ): HttpResponse =
        client.patch("$BASE_URL_PROFILE/change/change_profile_settings") {
            url {
                bearerAuth(accessToken)
                if (profileVisibility != null) parameters.append("profileVisibility", profileVisibility)
                if (language != null) parameters.append("language", language)
                if (colorTheme != null) parameters.append("colorTheme", colorTheme)
            }
        }

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/api/v1"
        private const val BASE_URL_AUTH = "$BASE_URL/auth"
        private const val BASE_URL_PROFILE = "$BASE_URL/profile"
    }
}