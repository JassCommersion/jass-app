package com.example.jass_app.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jass_app.data.model.MyProfile
import com.example.jass_app.data.service.HttpClientService
import com.example.jass_app.util.PreferencesManager
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val httpClientService: HttpClientService,
) : ViewModel() {
    private val _profileStatus = MutableLiveData(false)
    val profileStatus: LiveData<Boolean> = _profileStatus
    private val _profile = MutableLiveData<MyProfile>()
    val profile: LiveData<MyProfile> = _profile
    private val _accessToken = MutableLiveData("")
    val accessToken: LiveData<String> = _accessToken
    private val _refreshToken = MutableLiveData("")
    val refreshToken: LiveData<String> = _accessToken

    fun setTokens(newAccessToken: String, newRefreshToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _accessToken.postValue(newAccessToken)
            _refreshToken.postValue(newRefreshToken)
        }
    }

    fun getProfile(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val profileResponse =
                httpClientService.getUserProfile(accessToken)
            Log.d("PROFILE", profileResponse.toString())
            when(profileResponse.status) {
                HttpStatusCode.OK -> {
                    _profileStatus.postValue(true)
                    _profile.postValue(profileResponse.body<MyProfile>())
                }
            }
        }
    }

}