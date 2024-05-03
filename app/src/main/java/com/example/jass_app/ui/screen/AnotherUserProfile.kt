package com.example.jass_app.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jass_app.R
import com.example.jass_app.ui.component.CustomButton
import com.example.jass_app.data.model.Post
import com.example.jass_app.ui.theme.ButtonGradient
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun AnotherUserProfile(
    ProfileData: Any
) {
    val isPublic = false // TODO: Make publicity

    val buttonModifier = Modifier
        .fillMaxWidth()
    val buttonBorder = BorderStroke(2.dp, ButtonGradient)

    var posts = mutableListOf(
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
        Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar),
    ) // TODO: Make VM

    CollapsingToolbarScaffold(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 15.dp, end = 15.dp),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            Column(
                modifier = Modifier.pin()
            ) {
                ProfileHeader("really_long_long_long_long_long_username", 1)
                Divider(modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp))
            }
        }) {
        Column(
            Modifier.fillMaxHeight()
        ) {
            Column(
                Modifier.verticalScroll(rememberScrollState())
            ) {
                AvatarBlock(avatarId = 1, userFullName = "Vin Diesel")
                CustomButton(
                    text = { Text(stringResource(id = R.string.add_to_friend)) },
                    onClick = {
//                        TODO: Add to friend
                    }, modifier = buttonModifier, border = buttonBorder
                )
                Divider(modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))
                if (isPublic) {
                    FriendsBlock(
                        listOf(
                            "Andrew Tate",
                            "Barack Obama",
                            "Eshgin Magerramov",
                            "Joe Biden",
                            "Vladimir Putin"
                        )
                    )
                    Divider(modifier = Modifier.padding(top = 5.dp, bottom = 10.dp))
                } else {
                    PrivateProfileMessage()
                }
            }
            if (isPublic) {
                PhotoGrid(
                    posts
                )
            }
        }
    }
}


@Preview
@Composable
fun PrivateProfileMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.lock_icon),
            contentDescription = "Lock Icon"
        )
        Text(
            text = stringResource(id = R.string.private_profile_message)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun AnotherUserProfilePreview() {
    AnotherUserProfile(ProfileData = Any())
}