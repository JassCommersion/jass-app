package com.example.jass_app.ui.component

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jass_app.ui.theme.FocusedTextFieldColor
import com.example.jass_app.ui.theme.UnfocusedTextFieldColor

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    onOtpTextChange: (String, Boolean) -> Unit,
) {

    BasicTextField(
        modifier = modifier,
        singleLine = true,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(index, otpText, textModifier)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    modifier: Modifier
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = modifier,
        text = char,
        color = if (isFocused) {
            FocusedTextFieldColor
        } else {
            UnfocusedTextFieldColor
        },
        textAlign = TextAlign.Center,

        )
}

@Composable
@Preview
fun OtpTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    val mContext = LocalContext.current

    OtpTextField(
        otpText = text,
        onOtpTextChange = { value, otpInputFilled ->
            text = value
            Toast.makeText(mContext, "Change", Toast.LENGTH_LONG).show()
        },
        textModifier = Modifier
            .border(2.dp, Color.Black)
            .padding(horizontal = 5.dp)
            .width(20.dp)

    )
}