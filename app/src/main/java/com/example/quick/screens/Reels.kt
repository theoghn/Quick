package com.example.quick.screens

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.MoreVert

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.HorizontalAlign
import com.example.quick.R
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
            Interactions()
            Descriptions()
        }

    }

}
@Composable
fun Interactions(){
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
                Text(text = (100..10000).random().toString(), textAlign = TextAlign.Center,fontSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp, top = 3.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth(0.7f)
                        .weight(0.2f))
                Text(text = (100..1000).random().toString(), textAlign = TextAlign.Center,fontSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp, top = 3.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth(0.7f)
                        .weight(0.2f))
                Text(text = (100..3000).random().toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp, top = 3.dp))
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth(0.6f)
                        .weight(0.2f))
            }

        }
    }

}
@Composable
fun Descriptions(){
    ConstraintLayout(Modifier.fillMaxSize()){
        val (box) = createRefs()
        Box(
            modifier = Modifier
                .constrainAs(box) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth(.88f)
                .fillMaxHeight(0.15f)
                .background(Color.Transparent)
                .padding(10.dp)
        ){
            Column {
                Row(verticalAlignment = Alignment.CenterVertically){

                    CirclePhoto()
                    Spacer(modifier = Modifier.width(7.dp))
                    Text("Catty", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(7.dp))
                    OutlinedButton("Follow")

                }
                Spacer(modifier = Modifier.height(7.dp))
                Text ("This is the post description, Welcome!")
            }
        }
    }
}
@Composable
fun CirclePhoto(){
    Box(modifier = Modifier
        .border(
            1.dp, Brush.horizontalGradient(
                listOf(
                    Color(0xffff6f00),
                    Color(0xffffeb35),
                    Color(0xffff6f00),
                    Color(0xffff2b99),
                    Color(0xffff2bd1),
                    Color(0xffff2bd1),
                )
            ), CircleShape
        ).clip(CircleShape)
        .size(40.dp),
        contentAlignment = Alignment.Center
    ){
        Image(
            painterResource(id = R.drawable.cat),
            contentDescription = null
        )
    }

}

@Composable
fun OutlinedButton(
    text: String
){
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(7.dp)
            )
            .padding(top = 6.dp, bottom = 6.dp,start = 12.dp, end = 12.dp),
        contentAlignment = Alignment.Center,
    ){
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }
}



@Preview
@Composable
fun ReelsPreview() {
    QuickTheme(useDarkTheme = true) {
        ReelsPage()
    }
}