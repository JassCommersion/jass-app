package com.example.jass_app.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jass_app.R

@Composable
@Preview(showBackground = true)
fun Settings() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        SettingsHeader()
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
        )
        SettingsBlock()
//        TODO: Add save button and check for changes
    }
}

@Composable
@Preview(showBackground = true)
fun SettingsHeader() {
        Text(
            text = stringResource(id = R.string.settings), fontSize = 32.sp
        )
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBlock() {
    var expandedLang by remember { mutableStateOf(false) }
    var expandedVisibility by remember { mutableStateOf(false) }
    var expandedTheme by remember { mutableStateOf(false) }

    val langs = listOf("ru", "en")
    val visibilityOptions = listOf("Public", "Friends only", "Closed")
    val themes = listOf("Dark", "Light")

    var selectedLang by remember { mutableStateOf(langs[0]) }
    var selectedVisibility by remember { mutableStateOf(visibilityOptions[0]) }
    var selectedTheme by remember { mutableStateOf(themes[0]) }


    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.language),
                fontSize = 24.sp
                )
            ExposedDropdownMenuBox(
                expanded = expandedLang,
                onExpandedChange = {
                    expandedLang = !expandedLang
                },
                modifier = Modifier
            ) {
                TextField(
                    value = selectedLang,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLang) },
                    modifier = Modifier
                        .menuAnchor()
                        .width(90.dp),
                    shape = RoundedCornerShape(15),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )
                ExposedDropdownMenu(
                    expanded = expandedLang,
                    onDismissRequest = { expandedLang = false }) {
                    langs.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(it)
                            },
                            onClick = {
                                selectedLang = it
                                expandedLang = false
                            })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.visibility),
                fontSize = 24.sp
            )
            ExposedDropdownMenuBox(
                expanded = expandedVisibility,
                onExpandedChange = {
                    expandedVisibility = !expandedVisibility
                },
                modifier = Modifier
            ) {
                TextField(
                    value = selectedVisibility,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedVisibility) },
                    modifier = Modifier
                        .menuAnchor()
                        .width(150.dp),
                    shape = RoundedCornerShape(15),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )
                ExposedDropdownMenu(
                    expanded = expandedVisibility,
                    onDismissRequest = { expandedVisibility = false }) {
                    langs.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(it)
                            },
                            onClick = {
                                selectedVisibility = it
                                expandedVisibility = false
                            })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.theme),
                fontSize = 24.sp
            )
            ExposedDropdownMenuBox(
                expanded = expandedTheme,
                onExpandedChange = {
                    expandedTheme = !expandedTheme
                },
                modifier = Modifier
            ) {
                TextField(
                    value = selectedTheme,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTheme) },
                    modifier = Modifier
                        .menuAnchor()
                        .width(150.dp),
                    shape = RoundedCornerShape(15),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )
                ExposedDropdownMenu(
                    expanded = expandedTheme,
                    onDismissRequest = { expandedTheme = false }) {
                    langs.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(it)
                            },
                            onClick = {
                                selectedTheme = it
                                expandedTheme = false
                            })
                    }
                }
            }
        }
    }
}