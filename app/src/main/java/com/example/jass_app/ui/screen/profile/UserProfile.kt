package com.example.jass_app.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jass_app.R
import com.example.jass_app.ui.component.CustomButton
import com.example.jass_app.data.model.Post
import com.example.jass_app.data.viewmodel.ProfileViewModel
import com.example.jass_app.ui.Routes
import com.example.jass_app.ui.screen.PostDialog
import com.example.jass_app.ui.theme.BackgroundGray
import com.example.jass_app.ui.theme.ButtonGradient
import com.example.jass_app.util.PreferencesManager
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Composable
fun UserProfile(
    navigateTo : (route : String) -> Unit,
    viewModel: ProfileViewModel
) {
    val buttonModifier = Modifier
        .fillMaxWidth()
    val buttonBorder = BorderStroke(2.dp, ButtonGradient)

    val profileStatus by viewModel.profileStatus.observeAsState()
    val profile by viewModel.profile.observeAsState()

    val preferencesManager = PreferencesManager(LocalContext.current)

    LaunchedEffect(Unit) {
        viewModel.getProfile(preferencesManager.getData("accessToken", ""))
    }

    if (profileStatus == true) {
        Surface(
            modifier = Modifier.background(BackgroundGray)
        ) {
            CollapsingToolbarScaffold(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
                state = rememberCollapsingToolbarScaffoldState(),
                scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
                toolbar = {
                    Column(
                        modifier = Modifier.pin()
                    ) {
                        profile?.userName?.let { ProfileHeader(it, 1) }
                        HorizontalDivider(
                            modifier = Modifier.padding(
                                vertical = 10.dp,
                                horizontal = 15.dp
                            )
                        )
                    }
                }) {
                Column(
                    Modifier.fillMaxHeight()
                ) {
                    Column(
                        Modifier.verticalScroll(rememberScrollState())
                    ) {
                        AvatarBlock(avatarId = 1, userFullName = "Vin Diesel")
                        HorizontalDivider(modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))
                        profile?.friendsIds?.let { FriendsBlock(friendsList = it.map { toString() }) }
                        HorizontalDivider(modifier = Modifier.padding(top = 5.dp, bottom = 10.dp))
                        CustomButton(
                            text = { Text(stringResource(id = R.string.add_post), color = Color.White) },
                            onClick = {
//                            profile?.images.add(getNewPost())
                            }, modifier = buttonModifier, border = buttonBorder
                        )
                    }
                    PhotoGrid(
//                    posts
                    )
                }
            }
        }
    } else {

        Column(
            modifier = Modifier.fillMaxSize().background(BackgroundGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.loading))
        }
    }
}

fun getNewPost(): Post {
    return Post(userImage = R.drawable.test_avatar, post = R.drawable.test_avatar)
}

@Composable
fun PhotoGrid(photos: MutableList<Post> = mutableListOf<Post>()) {
    val openDialog = remember { mutableStateOf(false) }
    var post by remember { mutableStateOf(Post()) }
    var whenDialogShow = 0L

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.Top,
    ) {
        items(photos) { item ->
            Box(
                modifier = Modifier.padding(4.dp)
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = item.post),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1F)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    whenDialogShow = System.currentTimeMillis()
                                    openDialog.value = true
                                    post = item
                                },
                                onPress = {
                                    awaitRelease()
                                    if (System.currentTimeMillis() - whenDialogShow < 500) {
                                        openDialog.value = false
                                    }
                                }
                            )
                        }
                )
            }
        }
    }
    if (openDialog.value) {
        PostDialog(
            post = post,
        ) {
            openDialog.value = false
        }
    }
}

@Composable
fun FriendsBlock(friendsList: List<String>) {
    Text(
        text = stringResource(id = R.string.friends),
        modifier = Modifier.padding(start = 3.dp),
        fontSize = 16.sp
    )
    LazyRow {
        items(friendsList) { friend ->
            FriendCard(name = friend, photo = ImageBitmap.imageResource(R.drawable.test_avatar))
        }
    }

}

@Composable
fun FriendCard(name: String, photo: ImageBitmap) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .width(80.dp)
    ) {
        Image(
            bitmap = photo,
            contentDescription = "Friend's photo",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            style = TextStyle(fontSize = 14.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

//@Composable
//@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
//fun UserProfilePreview() {
//    UserProfile()
//}

@Composable
fun AvatarBlock(avatarId: Int, userFullName: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.test_avatar),
            contentDescription = "Profile photo",
            modifier = Modifier
                .size(200.dp)
                .aspectRatio(1F, matchHeightConstraintsFirst = true)
                .padding(3.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = userFullName,
            fontSize = 24.sp
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.more_info)
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.info_icon),
                contentDescription = "Image info icon",
                modifier = Modifier.padding(start = 3.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader(userName: String, userId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = userName,
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(0.1f),
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp),
            contentPadding = PaddingValues(
                start = 4.dp,
                top = 4.dp,
                end = 4.dp,
                bottom = 4.dp,
            ),
            shape = RoundedCornerShape(20),
            onClick = {
                /*TODO*/
            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.more),
                contentDescription = "Profile more button"
            )
        }
    }
}