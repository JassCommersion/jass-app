package com.example.jass_app.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jass_app.R
import com.example.jass_app.ui.component.CustomButton
import com.example.jass_app.ui.component.CustomOutlinedTextField
import com.example.jass_app.ui.theme.ButtonGradient

@Preview(showBackground = true)
@Composable
fun ResetPassword() {

    var isCorrectEmail by remember { mutableStateOf(true) }

    val buttonModifier = Modifier
        .fillMaxWidth()
    val buttonBorder = BorderStroke(2.dp, ButtonGradient)

    val textFieldModifier = Modifier
        .fillMaxWidth()
//        .padding(vertical = 2.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Column {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.reset),
                contentDescription = "Confirm label"
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.password),
                contentDescription = "Confirm label"
            )

        }
        Text(
            text = stringResource(id = R.string.confirmation_title_text),
            modifier = Modifier.padding(vertical = 10.dp)
        )


        CustomOutlinedTextField(
            hintText = stringResource(id = R.string.email),
            keyboardType = KeyboardType.Email,
            supportingText = stringResource(R.string.incorrect_email_message),
            isError = !isCorrectEmail,
            modifier = textFieldModifier
        )

        CustomButton(
            text = {
                Text(stringResource(id = R.string.continue_string))
            },
            onClick = {
                isCorrectEmail = !isCorrectEmail
//                TODO
            },
            modifier = buttonModifier,
            border = buttonBorder
        )
    }
}