package com.example.jass_app.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jass_app.R
import com.example.jass_app.ui.component.CustomButton
import com.example.jass_app.ui.component.CustomOutlinedTextField
import com.example.jass_app.ui.theme.ButtonGradient


@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun Authorisation() {

    var isCorrectEmail by remember { mutableStateOf(true) }
    var isCorrectPassword by remember { mutableStateOf(true) }

    val buttonModifier = Modifier
        .fillMaxWidth()
    val buttonBorder = BorderStroke(2.dp, ButtonGradient)

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(1.dp))
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.jass_logo_black),
                    contentDescription = "Jass logo",
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))

                AuthorisationTextFieldBlock(
                    isCorrectEmail, isCorrectPassword
                )

                CustomButton(text = { Text(stringResource(id = R.string.continue_string)) },
                    onClick = {
                        isCorrectEmail = !isCorrectEmail
                        isCorrectPassword = !isCorrectPassword
                    },
                    modifier = buttonModifier,
                    border = buttonBorder
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        CustomButton(text = { Text(stringResource(id = R.string.sign_up)) },
                            onClick = {
                                isCorrectEmail = !isCorrectEmail
                                isCorrectPassword = !isCorrectPassword
                            },
                            modifier = buttonModifier,
                            border = buttonBorder
                        )
                    }
                    Column(modifier = Modifier.weight(0.05f)) {}
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        CustomButton(text = { Text(stringResource(id = R.string.sign_in)) },
                            onClick = {
                                isCorrectEmail = !isCorrectEmail
                                isCorrectPassword = !isCorrectPassword
                            },
                            modifier = buttonModifier,
                            border = buttonBorder
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.forget_password),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clickable {
                            /* TODO */
                        }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(0.45f)
                    ) {
                        Divider()
                    }
                    Column(
                        modifier = Modifier.weight(0.1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.or))
                    }
                    Column(
                        modifier = Modifier.weight(0.45f)
                    ) {
                        Divider()
                    }
                }
                CustomButton(
                    text = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.sign_up_google),
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                        }
                    },
                    onClick = { /*TODO*/ },
                    icon = painterResource(R.drawable.google_logo),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}

@Composable
fun AuthorisationTextFieldBlock(
    isCorrectEmail: Boolean, isCorrectPassword: Boolean
) {

    val textFieldModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)

    CustomOutlinedTextField(
        hintText = stringResource(id = R.string.email),
        keyboardType = KeyboardType.Email,
        supportingText = stringResource(R.string.incorrect_email_message),
        isError = !isCorrectEmail,
        modifier = textFieldModifier
    )
    CustomOutlinedTextField(
        hintText = stringResource(id = R.string.password),
        keyboardType = KeyboardType.Password,
        isPasswordToggleEnabled = true,
        supportingText = stringResource(R.string.incorrect_password_message),
        isError = !isCorrectPassword,
        modifier = textFieldModifier
    )
}

/*
TODO - Add styles on buttons and field.
TODO - Import correct logos
 */