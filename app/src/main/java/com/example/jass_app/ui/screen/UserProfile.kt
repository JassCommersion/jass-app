package com.example.jass_app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jass_app.R
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


@Composable

fun UserProfile(
    UserProfileData: Any
) {
    Column {
        ProfileHeader("really_long_long_long_long_long_username", 1)
        Divider(modifier = Modifier.padding(vertical = 10.dp))
    }

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 50.dp),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            Box(modifier = Modifier.pin())
            Column {
                AvatarBlock(avatarId = 1, userFullName = "Vin Diesel")
                Divider(modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))
                FriendsBlock(listOf("Andrew Tate", "Barack Obama"))
                Divider(modifier = Modifier.padding(top = 5.dp, bottom = 10.dp))
            }
        }) {

        PhotoGrid(
            listOf(
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
                ImageBitmap.imageResource(R.drawable.test_avatar),
            )
        )


    }

}


@Composable
fun PhotoGrid(photos: List<ImageBitmap>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(photos) { photo ->
            PhotoItem(photo = photo)
        }
    }
}

@Composable
fun PhotoItem(photo: ImageBitmap, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(4.dp)
    ) {
        Image(
            bitmap = photo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
        )
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
        modifier = Modifier.padding(8.dp)
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

@Composable
@Preview(showBackground = true)
fun UserProfilePreview() {
    UserProfile(UserProfileData = Any())
}

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

@Composable
//@Preview(showBackground = true)
fun AvatarBlockPreview() {
    AvatarBlock(1, "Vin Diesel")
}

@Composable
//@Preview(showBackground = true)
fun ProfileHeaderPreview() {
    ProfileHeader(userName = "andrew_tate", userId = 1)
}