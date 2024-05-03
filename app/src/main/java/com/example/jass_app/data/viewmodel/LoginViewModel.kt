package com.example.jass_app.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jass_app.data.model.request.LoginRequest
import com.example.jass_app.data.model.request.RegistrationRequest
import com.example.jass_app.data.model.response.LoginResponse
import com.example.jass_app.data.service.HttpClientService
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val httpClientService: HttpClientService
) : ViewModel() {

    private val _connectionStatus = MutableLiveData<HttpStatusCode>()
    val connectionStatus: LiveData<HttpStatusCode> = _connectionStatus
    private val _loginStatus = MutableLiveData(false)
    val loginStatus: LiveData<Boolean> = _loginStatus
    private val _accessToken = MutableLiveData("")
    val accessToken: LiveData<String> = _accessToken
    private val _refreshToken = MutableLiveData("")
    val refreshToken: LiveData<String> = _accessToken

    var emailValue = MutableLiveData("")
    val passwordValue = MutableLiveData("")

    fun checkConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseStatusCode = httpClientService.authTestConnection().status
            Log.d("CONNECTION", responseStatusCode.toString())
            _connectionStatus.postValue(responseStatusCode)
        }
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseLogin =
                httpClientService.login(LoginRequest(emailValue.value!!, passwordValue.value!!))
            Log.d("LOGIN", responseLogin.toString())
            when (responseLogin.status) {
                HttpStatusCode.OK -> {
                    _loginStatus.postValue(true)
                    _accessToken.postValue(responseLogin.body<LoginResponse>().accessToken)
                    _refreshToken.postValue(responseLogin.body<LoginResponse>().refreshToken)
                }
            }
        }
    }
}
