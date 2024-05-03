package com.example.jass_app.di

import com.example.jass_app.data.service.HttpClientService
import com.example.jass_app.data.service.impl.HttpClientServiceImpl
import com.example.jass_app.data.viewmodel.LoginViewModel
import com.example.jass_app.data.viewmodel.ProfileViewModel
import com.example.jass_app.data.viewmodel.RegistrationViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import org.koin.androidx.viewmodel.dsl.viewModel
import io.ktor.serialization.gson.*
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                gson()
            }
        }
    }

    single { HttpClientServiceImpl(get()) } bind HttpClientService::class
}

val viewModelModule = module {
    viewModel { RegistrationViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}


val appModule = module {

} + networkModule + viewModelModule