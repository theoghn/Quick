package com.example.quick.screens.home

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.quick.R
import com.example.quick.models.UserDetails
import com.example.quick.screens.profile.ProfileViewModel
import com.example.quick.ui.theme.QuickTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@Composable
fun Home(viewModel: HomeViewModel) {
    val posts by viewModel.posts.collectAsState()
    val users by viewModel.users.collectAsState()
    val numberOfReq by viewModel.numberOfRequests.collectAsState()

    Scaffold(
        topBar = {
            Text(
                text = "Quick Discover",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        },
        content = { padding ->
            val height = getScreenHeight() / 2
            val listState = rememberLazyListState()
            LaunchedEffect(listState) {
                snapshotFlow { listState.firstVisibleItemIndex }
                    .map { index -> index == numberOfReq.toInt()*4 -3 }
                    .distinctUntilChanged()
                    .filter { it }
                    .collect {
                        viewModel.getMorePosts()
                    }
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Log.d ("Home View", posts.count().toString())

                items(posts) { post ->
                    val user :UserDetails = users.first {it.userId == post.userId}
                    if(user.userId != ""){
                        PostCard(height, user.username, post.caption, post.likeCount, user.picture,post.media)
                    }
                    else{
                        Log.d ("Home View", "user invalid")
                    }
                }

            }

        }
    )
}

@Composable
fun PostCard(
    height: Int,
    username: String,
    caption: String,
    likeNumber: Number,
    userPhoto: String,
    mediaPhoto: String,

) {
    val top = height * .1
    val photo = height * .7
    val bottom = height * .2

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            CirclePhoto(userPhoto)
            Spacer(modifier = Modifier.width(8.dp))

            Text(username, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .weight(7.5f),
            model = mediaPhoto, contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
        ) {
            InteractionButtons()
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp)
                .weight(2f)

        ) {
            CaptionSection(likeNumber, caption, username)
        }

    }
}

fun getScreenHeight(): Int {
    val height = Resources.getSystem().displayMetrics.heightPixels
    val density = Resources.getSystem().displayMetrics.density
    return height / density.toInt()
}

@Composable
fun CirclePhoto(userPhoto: String) {

    Box(
        modifier = Modifier
            .border(
                1.dp, Color.Cyan, CircleShape
            )
            .clip(CircleShape)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = userPhoto,
            contentDescription = null,

            )
    }

}

@Composable
fun InteractionButtons() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            Modifier
                .fillMaxHeight()
                .padding(start = 5.dp, end = 10.dp)
                .aspectRatio(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_comment),
            contentDescription = null,
            Modifier
                .fillMaxHeight(0.9f)
                .padding(start = 10.dp, end = 10.dp)

        )
        Icon(
            painter = painterResource(id = R.drawable.ic_send),
            contentDescription = null,
            Modifier
                .fillMaxHeight(0.9f)
                .padding(start = 10.dp, end = 8.dp)

        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.BookmarkBorder,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(1f)
                .padding(start = 8.dp, end = 8.dp)
                .aspectRatio(1f)

        )

    }

}

@Composable
fun CaptionSection(likeNumber: Number, caption: String, username: String) {
    val text = "$username $caption"
    val parts = text.split(" ")
    val annotatedString = buildAnnotatedString {
        if (parts.isNotEmpty()) {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(parts[0])
            }
            for (i in 1 until parts.size) {
                append(" ${parts[i]}")
            }
        }
    }
    Column {
        Text(
            text = "${likeNumber.toString()} likes",
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        Text(text = annotatedString, fontSize = 15.sp)

    }
}

//@Preview
//@Composable
//fun GreetingPreview() {
//    QuickTheme(useDarkTheme = true) {
//        Home()
//    }
//}
