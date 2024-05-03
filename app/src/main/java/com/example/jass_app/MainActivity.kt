package com.example.jass_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jass_app.data.viewmodel.LoginViewModel
import com.example.jass_app.data.viewmodel.RegistrationViewModel
import com.example.jass_app.di.appModule
import com.example.jass_app.ui.ComposeNav
import com.example.jass_app.ui.screen.Registration
import com.example.jass_app.ui.theme.JassappTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDI()
        val RegistrationViewModel = getViewModel<RegistrationViewModel>()
        val AuthtorizationViewModel = getViewModel<LoginViewModel>()

        setContent {
            JassappTheme {
                // A surface container using the 'background' color from the theme
                Greeting(name = "Hello, Android")
                    ComposeNav(RegistrationViewModel, AuthtorizationViewModel)
            }
        }
    }

    private fun initDI() {
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JassappTheme {
        Greeting("Android")
    }
}