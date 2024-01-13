package com.example.quick.screens.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AssignmentInd
import androidx.compose.material.icons.outlined.Dehaze
import androidx.compose.material.icons.outlined.GridOn
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quick.R
import com.example.quick.screens.splash.SplashViewModel
import com.example.quick.ui.theme.QuickTheme

@Composable
fun CenteredText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Center,
    fontSize: TextUnit = 14.sp

    // Add any other common properties here
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        textAlign = textAlign,
        modifier = modifier.fillMaxWidth(),
        fontSize = fontSize
        // Add any other common properties here
    )
}

@Composable
fun Profile(viewModel: ProfileViewModel = hiltViewModel()) {
    ConstraintLayout(Modifier.fillMaxSize(1f)) {
        val guu = createGuidelineFromTop(fraction = 0.05f)
        val guide = createGuidelineFromTop(fraction = 0.20f)
        val guide2 = createGuidelineFromTop(fraction = 0.35f)
        val guide3 = createGuidelineFromTop(fraction = 0.40f)
        val guide4 = createGuidelineFromTop(fraction = 0.55f)
        val guide5 = createGuidelineFromTop(fraction = 0.63f)



        val (bar, picture, stats, description, buttons, stories, posts) = createRefs()
        TopBar(modifier = Modifier.constrainAs(bar) {
            height = Dimension.fillToConstraints
            top.linkTo(parent.top, margin = 5.dp)
            bottom.linkTo(guu, margin = 5.dp)
        },viewModel)

        Box(
            Modifier
                .constrainAs(picture) {
                    height = Dimension.fillToConstraints
                    top.linkTo(guu, margin = 5.dp)
                    bottom.linkTo(guide, margin = 5.dp)
                }
                .fillMaxWidth(0.3f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            ProfilePic()
        }

        Row(
            Modifier
                .constrainAs(stats) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(guu, margin = 5.dp)
                    start.linkTo(picture.end, margin = 5.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                    bottom.linkTo(guide, margin = 5.dp)
                }
        ) {
            ProfileStats()
        }

        Column(
            Modifier
                .constrainAs(description) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(guide, margin = 5.dp)
                    bottom.linkTo(guide2, margin = 5.dp)
                    start.linkTo(parent.start, margin = 5.dp)
                    end.linkTo(parent.end, margin = 5.dp)

                }

        ) {
            ProfileDetails()
        }

        Row(
            Modifier
                .constrainAs(buttons) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(guide2, margin = 5.dp)
                    start.linkTo(parent.start, margin = 5.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                    bottom.linkTo(guide3, margin = 5.dp)
                }
        ) {
            EditShareButtons()
        }

        Row(
            Modifier
                .constrainAs(stories) {
                    height = Dimension.fillToConstraints
                    top.linkTo(guide3, margin = 5.dp)
                    bottom.linkTo(guide4, margin = 5.dp)
                }
        ) {
            StoriesArchive()
        }

        Row(
            Modifier
                .constrainAs(posts) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(guide4, margin = 5.dp)
                    bottom.linkTo(guide5, margin = 5.dp)
                    start.linkTo(parent.start, margin = 5.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                }
        ) {
            PostsTagsButtons()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier,viewModel: ProfileViewModel) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    Row(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(modifier = Modifier.fillMaxWidth(0.4f)) {
            Text(
                text = "Catty",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
            Icon(
                imageVector = Icons.Outlined.Dehaze,
                contentDescription = "Profile Menu",
                Modifier
                    .padding(end = 10.dp)
                    .fillMaxWidth(0.2f)
                    .fillMaxHeight()
                    .clickable { isSheetOpen = true }

            )
        }
    }
    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            },
        ) {
            Column(Modifier.fillMaxWidth().fillMaxHeight(0.2f)){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.40f)
                    .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Log out",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onLogOutClick()
                            })
                }
            }

        }
    }
}

@Composable
fun ProfilePic() {
    Box(
        modifier = Modifier
            .clip(CircleShape),
    ) {
        Image(painter = painterResource(id = R.drawable.cat), contentDescription = "profile pic")
    }
}

@Composable
fun ProfileStats() {
//the size is defined like this because i didn't think to use another column so i can use the weight modifier
    Box(
        modifier = Modifier
            .fillMaxWidth(0.33f)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CenteredText(text = "0", fontWeight = FontWeight.Bold)
            CenteredText(text = "Posts")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CenteredText(text = "223", fontWeight = FontWeight.Bold)
            CenteredText(text = "Followers")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    )
    {
        Column {
            CenteredText(text = "432", fontWeight = FontWeight.Bold)
            CenteredText(text = "Following")
        }
    }
}

@Composable
fun ProfileDetails() {
    val context = LocalContext.current
    //here i just use another column that matches the father column to use weight modifier
    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(weight = 0.3f, fill = true)
                .fillMaxSize()
        ) {
            Text(text = "Catty", fontWeight = FontWeight.Bold)
        }
        Box(
            modifier = Modifier
                .weight(weight = 0.3f, fill = true)
                .fillMaxSize()
        )
        {
            Text(text = "I am a cat")
        }
        Box(
            modifier = Modifier
                .weight(weight = 0.3f, fill = true)
                .fillMaxSize(),

            ) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.Link, contentDescription = null,
                    Modifier
                        .rotate(-30f)
                        .padding(top = 8.dp)
                )
                ClickableText(
                    text = AnnotatedString("https://www.twitch.tv/omie"),
                    onClick = {
                        val openURL =
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitch.tv/omie"))
                        startActivity(context, openURL, null)
                    },
                    style = TextStyle(
                        color = Color.Cyan,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()


                )
            }
        }
    }
}

@Composable
fun EditShareButtons() {
    Row {
        RoundedButton(
            text = "Edit Profile",
            modifier = Modifier
                .padding(start = 2.dp)
                .weight(weight = 0.5f, fill = true)
        )
        RoundedButton(
            text = "Share Profile",
            modifier = Modifier
                .padding(start = 5.dp)
                .weight(weight = 0.5f, fill = true)
        )
    }
}

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(6.dp))
            .clickable { }
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center

    ) {
        CenteredText(text = text, fontWeight = FontWeight.Bold, fontSize = 10.sp)
    }
}

@Composable
fun StoriesArchive() {
    LazyRow {
        items(8) {
            Box(
                Modifier
                    .aspectRatio(1f)
                    .padding(8.dp)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            )
        }
    }

}

@Composable
fun PostsTagsButtons() {
    Row {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .weight(weight = 0.5f, fill = true)
                .fillMaxSize()
                .clickable { },
            contentAlignment = Alignment.Center
//            shape = RectangleShape,
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Icon(
                imageVector = Icons.Outlined.GridOn,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(0.7f)
            )
        }
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .weight(weight = 0.5f, fill = true)
                .fillMaxSize()
                .clickable { },
            contentAlignment = Alignment.Center

        ) {
            Icon(
                imageVector = Icons.Outlined.AssignmentInd,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(0.7f)
            )
        }
    }

}


@Preview
@Composable
fun ProfilePreviews() {
    QuickTheme(useDarkTheme = true) {
        Profile()
    }
}