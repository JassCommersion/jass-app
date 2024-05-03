package com.example.jass_app.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appmason.jetplayground.ui.components.OtpInputField
import com.example.jass_app.R
import com.example.jass_app.ui.component.CustomButton
import com.example.jass_app.ui.theme.BackgroundGray
import com.example.jass_app.ui.theme.ButtonGradient


@Preview(showBackground = true)
@Composable
fun Confirmation() {

    var otpValue by remember { mutableStateOf("") }
    var isOtpFilled by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(true) }

    val incorrectStatusModifier = Modifier

    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
    val buttonBorder = BorderStroke(2.dp, ButtonGradient)

    Surface(
        modifier = Modifier.fillMaxSize().background(BackgroundGray)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.confirm),
                    contentDescription = "Confirm label"
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.email),
                    contentDescription = "Confirm label"
                )
            }
            Text(
                text = stringResource(id = R.string.confirmation_title_text),
                modifier = Modifier.padding(vertical = 10.dp),
                fontSize = 19.sp
            )
            OtpInputField(
                modifier = Modifier.fillMaxWidth(),
                otpText = otpValue,
                shouldCursorBlink = false,
                onOtpModified = { value, otpFilled ->
                    otpValue = value
                    isOtpFilled = otpFilled
                })

            if (!isCorrect) {
                Text(
                    text = stringResource(id = R.string.incorrect_verification_code),
                    modifier = incorrectStatusModifier
                )
            }

            CustomButton(
                text = {
                    Text(stringResource(id = R.string.continue_string))
                },
                onClick = {
                    isCorrect = !isCorrect
//                TODO
                },
                modifier = buttonModifier,
                border = buttonBorder
            )
        }
    }
}