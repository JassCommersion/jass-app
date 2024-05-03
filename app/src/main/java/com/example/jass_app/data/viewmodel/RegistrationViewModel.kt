package com.example.jass_app.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jass_app.data.model.request.RegistrationRequest
import com.example.jass_app.data.model.response.RegistrationResponse
import com.example.jass_app.data.service.HttpClientService
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val httpClientService: HttpClientService
) : ViewModel() {

    private val _connectionStatus = MutableLiveData<HttpStatusCode>()
    val connectionStatus: LiveData<HttpStatusCode> = _connectionStatus
    private val _registrationStatus = MutableLiveData(false)
    val registrationStatus: LiveData<Boolean> = _registrationStatus
    private val _accessToken = MutableLiveData("")
    val accessToken: LiveData<String> = _accessToken
    private val _refreshToken = MutableLiveData("")
    val refreshToken: LiveData<String> = _accessToken

    var emailValue = MutableLiveData("")
    val passwordValue = MutableLiveData("")
    val passwordRepeatValue = MutableLiveData("")

    fun checkConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseStatusCode = httpClientService.authTestConnection().status
            Log.d("CONNECTION", responseStatusCode.toString())
            _connectionStatus.postValue(responseStatusCode)
        }
    }

    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseRegistration =
                httpClientService.register(
                    RegistrationRequest(
                        emailValue.value!!,
                        passwordValue.value!!
                    )
                )
            Log.d("REGISTRATION", responseRegistration.toString())
            when (responseRegistration.status) {
                HttpStatusCode.Created -> {
                    _registrationStatus.postValue(true)
                    _accessToken.value =
                        responseRegistration.body<RegistrationResponse>().accessToken
                    _refreshToken.value =
                        responseRegistration.body<RegistrationResponse>().refreshToken
                }
            }
        }
    }
}
