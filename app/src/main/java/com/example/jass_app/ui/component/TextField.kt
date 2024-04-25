package com.example.jass_app.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    hintText: String,
    hintModifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText: String,
    supportingModifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleEnabled: Boolean = false,
    allowFreeFormInput: Boolean = false
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textValue,
        onValueChange = { newValue ->
            textValue = newValue
        },
        placeholder = {
            Text(
                text = hintText,
                modifier=hintModifier
            )
                      },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = if (isPasswordToggleEnabled && !passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = !allowFreeFormInput,
        trailingIcon = {
            if (isPasswordToggleEnabled) {
                val icon = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(icon, contentDescription = "Toggle password visibility")
                }
            }
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = supportingText, modifier = supportingModifier)
            }
        },
        modifier = modifier
    )
}

@Composable
@Preview
fun CustomOutlinedTextFieldPreview() {
    CustomOutlinedTextField(
        hintText = "Password",
        keyboardType = KeyboardType.Password,
        isPasswordToggleEnabled = true,
        modifier = Modifier.fillMaxWidth(),
        supportingText = "Just text",
        isError = true
    )
}