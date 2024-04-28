package com.example.jass_app.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import com.example.jass_app.R
import com.example.jass_app.ui.component.CustomButton
import com.example.jass_app.ui.component.CustomOutlinedTextField
import com.example.jass_app.ui.theme.ButtonGradient

@Preview(showBackground = true)
@Composable
fun UpdatePassword() {

//    var isCorrectEmail by remember { mutableStateOf(true) }
    var isCorrectPassword by remember { mutableStateOf(true) }
    var isCorrectPasswordRepeat by remember { mutableStateOf(true) }

    val buttonModifier = Modifier
        .fillMaxWidth()
//        .padding(vertical = 5.dp)
    val buttonBorder = BorderStroke(2.dp, ButtonGradient)

    val textFieldModifier = Modifier
        .fillMaxWidth()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
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
                text = stringResource(id = R.string.repeat_password_title_text),
                modifier = Modifier.padding(vertical = 10.dp),
                fontSize = 19.sp
            )

            CustomOutlinedTextField(
                hintText = stringResource(id = R.string.password),
                keyboardType = KeyboardType.Password,
                isPasswordToggleEnabled = true,
                supportingText = stringResource(R.string.incorrect_password_message),
                isError = !isCorrectPassword,
                modifier = textFieldModifier
            )
            CustomOutlinedTextField(
                hintText = stringResource(id = R.string.repeat_password),
                keyboardType = KeyboardType.Password,
                isPasswordToggleEnabled = true,
                supportingText = stringResource(R.string.incorrect_password_repeat_message),
                modifier = textFieldModifier,
                isError = !isCorrectPasswordRepeat
            )

            CustomButton(
                text = {
                    Text(stringResource(id = R.string.continue_string))
                },
                onClick = {
                    isCorrectPassword = !isCorrectPassword
                    isCorrectPasswordRepeat = !isCorrectPasswordRepeat
//                TODO
                },
                modifier = buttonModifier,
                border = buttonBorder
            )
        }
    }
}