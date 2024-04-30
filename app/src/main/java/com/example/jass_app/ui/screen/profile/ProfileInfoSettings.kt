package com.example.jass_app.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jass_app.R
import com.example.jass_app.ui.component.CustomOutlinedTextField

@Composable
fun ProfileInfoSettings() {
    ProfileInfoSettingsHeader()
}

@Composable
@Preview(showBackground = true)
fun ProfileInfoSettingsHeader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.edit_personal_info), fontSize = 32.sp
        )
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
        )
        ProfileInfoSettingsBlock("Carl", "Simpson", "1", "2", "1999")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfoSettingsBlock(
    firstName: String,
    lastName: String,
    birthDay: String,
    birthMonth: String,
    birthYear: String
) {
    var expandedGender by remember { mutableStateOf(false) }
    var expandedCountry by remember { mutableStateOf(false) }

    val genders = listOf("Other", "Male", "Female")
    val countries = listOf("Other", "Russia", "USA", "France")

    var selectedFirstName by remember { mutableStateOf(firstName) }
    var selectedLastName by remember { mutableStateOf(lastName) }
    var selectedBirtDay by remember { mutableStateOf(birthDay) }
    var selectedBirtMonth by remember { mutableStateOf(birthMonth) }
    var selectedBirtYear by remember { mutableStateOf(birthYear) }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.first_name),
                fontSize = 24.sp
            )
            CustomOutlinedTextField(
                hintText = stringResource(id = R.string.first_name),
                supportingText = "",
                text = selectedFirstName,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.last_name),
                fontSize = 24.sp
            )
            CustomOutlinedTextField(
                hintText = stringResource(id = R.string.last_name),
                supportingText = "",
                text = selectedLastName,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.birt_date),
                fontSize = 24.sp
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomOutlinedTextField(
                    hintText = stringResource(id = R.string.last_name),
                    supportingText = "",
                    text = selectedLastName,
                    modifier = Modifier.fillMaxWidth().weight(0.5f),
                    )
                CustomOutlinedTextField(
                    hintText = stringResource(id = R.string.last_name),
                    supportingText = "",
                    text = selectedLastName,
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 10.dp),
                )
                CustomOutlinedTextField(
                    hintText = stringResource(id = R.string.last_name),
                    supportingText = "",
                    text = selectedLastName,
                    modifier = Modifier.fillMaxWidth().weight(1f),
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileInfoSettingsBlockPreview() {
    ProfileInfoSettingsBlock("Carl", "Simpson", "1", "2", "1999")
}