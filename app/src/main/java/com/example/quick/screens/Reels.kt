package com.example.quick.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.ModeComment

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.HorizontalAlign
import com.example.quick.ui.theme.QuickTheme
import kotlin.random.Random

@Composable
fun ReelsPage() {
    Box(Modifier.background(Color.Transparent)) {
        Reels()
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (icon) = createRefs()
            Icon(
                imageVector = Icons.Outlined.CameraAlt,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(icon) {
                        height = Dimension.fillToConstraints
                        top.linkTo(parent.top, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    }
                    .fillMaxWidth(.12f)
                    .aspectRatio(1f)
            )
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Reels() {
    val pagerState = rememberPagerState { 8 }
    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) {
        val randomColor = Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat(),
            alpha = 1f
        )
        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .background(randomColor)
        ) {
            Text(
                text = " ", Modifier
                    .fillMaxSize()
            )
            InteractionPart()
        }

    }

}
@Composable
fun InteractionPart(){
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (box) = createRefs()
        Box(modifier = Modifier
            .constrainAs(box) {
                end.linkTo(parent.end, margin = 5.dp)
                bottom.linkTo(parent.bottom, margin = 5.dp)
            }
            .fillMaxWidth(.12f)
            .fillMaxHeight(.36f)
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .weight(0.2f))
                Text(text = "100", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(bottom =10.dp))
                Icon(
                    imageVector = Icons.Outlined.ModeComment,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth(0.8f)
                        .weight(0.2f))
                Text(text = "300", textAlign = TextAlign.Center,modifier =  Modifier.fillMaxWidth().padding(bottom =10.dp))
                Icon(
                    imageVector = Icons.Outlined.Send,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth(0.8f)
                        .weight(0.2f))
                Text(text = "200", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(bottom =10.dp))
            }

        }
    }

}

@Preview
@Composable
fun ReelsPreview() {
    QuickTheme(useDarkTheme = true) {
        ReelsPage()
    }
}