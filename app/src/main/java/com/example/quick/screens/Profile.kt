package com.example.quick.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import androidx.constraintlayout.compose.atMost
import androidx.constraintlayout.compose.atMostWrapContent
import com.example.quick.MainScreen
import com.example.quick.ui.theme.QuickTheme
@Composable
fun CenteredText(
    modifier : Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Center

    // Add any other common properties here
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        textAlign = textAlign,
        modifier = modifier.fillMaxWidth()
        // Add any other common properties here
    )
}

@Composable
fun Profile() {
    ConstraintLayout(Modifier.fillMaxSize(1f)) {
        val guide = createGuidelineFromTop(fraction = 0.15f)
        val guide2 = createGuidelineFromTop(fraction = 0.30f)
        val guide3 = createGuidelineFromTop(fraction = 0.35f)
        val guide4 = createGuidelineFromTop(fraction = 0.50f)
        val guide5 = createGuidelineFromTop(fraction = 0.60f)



        val (picture, stats, description, buttons, stories, posts_tags) = createRefs()
        Box(
            Modifier
                .background(Color.Red)
                .constrainAs(picture) {
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top, margin = 5.dp)
                    bottom.linkTo(guide, margin = 5.dp)
                }
                .fillMaxWidth(0.3f)
                .fillMaxHeight()
        ) {
            Text(text = "bos")
        }
        Row(
            Modifier
                .constrainAs(stats) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top, margin = 5.dp)
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
            Box(
                modifier = Modifier
                    .background(Color.Cyan)
                    .weight(weight = 0.3f, fill = true)
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .weight(weight = 0.3f, fill = true)
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .background(Color.Magenta)
                    .weight(weight = 0.3f, fill = true)
                    .fillMaxSize()
            )

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
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .background(Color.Cyan)
                    .weight(weight = 0.4f, fill = true)
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .background(Color.Gray)
                    .weight(weight = 0.4f, fill = true)
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .background(Color.Magenta)
                    .weight(weight = 0.1f, fill = true)
                    .fillMaxSize()
            )
        }
        LazyRow(
            Modifier
                .constrainAs(stories) {
                    height = Dimension.fillToConstraints
                    top.linkTo(guide3, margin = 5.dp)
                    bottom.linkTo(guide4, margin = 5.dp)
                }
        ) {
            items(8) { index ->
                Box(
                    Modifier
                        .width(100.dp)
                        .padding(8.dp)
                        .background(Color.DarkGray)
                        .fillMaxHeight()
                )
            }
        }
        Row(
            Modifier
                .constrainAs(posts_tags) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(guide4, margin = 5.dp)
                    bottom.linkTo(guide5, margin = 5.dp)
                    start.linkTo(parent.start, margin = 5.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                }
        ) {
            Button(
                content = {
                Text(text = "Posts", color = Color.White)
            }, onClick = { /*TODO*/ },
                modifier = Modifier
                    .background(Color.Gray)
                    .weight(weight = 0.5f, fill = true)
                    .fillMaxSize()
            )
            Button(
                content = {
                    Text(text = "Tagged", color = Color.White)
                }, onClick = { /*TODO*/ },
                modifier = Modifier
                    .background(Color.Gray)
                    .weight(weight = 0.5f, fill = true)
                    .fillMaxSize()
            )
        }

    }
}

@Composable
fun ProfileStats(){

    Box(
        modifier = Modifier
            .fillMaxWidth(0.33f)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ){
        Column {
            CenteredText(text = "0",fontWeight = FontWeight.Bold)
            CenteredText(text = "Posts")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ){
        Column {
            CenteredText(text ="223",fontWeight = FontWeight.Bold)
            CenteredText(text ="Followers")
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
            CenteredText(text ="432",fontWeight = FontWeight.Bold)
            CenteredText(text ="Following")
        }
    }
}



@Preview()
@Composable
fun Previews() {
    QuickTheme(useDarkTheme = true) {
        Profile()
    }
}