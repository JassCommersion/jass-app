package com.example.jass_app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jass_app.R
import com.example.jass_app.data.model.Post

@Composable
fun PostDialog(post: Post, onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(post.post),
                contentDescription = "Post Dialog photo",
                contentScale = ContentScale.FillWidth
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostDialogPreview() {
    val post = Post(
        userImage = R.drawable.test_avatar,
        post = R.drawable.test_avatar
    )
    PostDialog(post) {

    }
}