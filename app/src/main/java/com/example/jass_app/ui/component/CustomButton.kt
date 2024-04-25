package com.example.jass_app.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    icon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        border = border
    ) {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null)
        }
        text()
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleSignInButtonPreview() {
    CustomButton(
        text = {
            Text(
                text = "Войти с помощью Google",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        },
        onClick = { /* Логика при нажатии */ },
        icon = Icons.Outlined.Android,
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GradientBorderButtonPreview() {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color.Blue, Color.Green)
    )
    CustomButton(
        text = {
            Text(
                text = "Кнопка с градиентной обводкой",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        onClick = { /* Логика при нажатии */ },
        modifier = Modifier
            .padding(16.dp),
        border = BorderStroke(2.dp, gradient)
    )
}

