package com.example.notesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesapp.data.classes.Page
import com.example.notesapp.data.classes.onBoardingPages
import com.example.notesapp.ui.theme.modernFamily
import com.example.notesapp.ui.theme.mohaveFamily

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
    pageIndex: Int
) {
    ElevatedCard (
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp)
            .clip(
                RoundedCornerShape(25.dp)
            ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 25.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomText(
                    text = "Notes",
                    textColor = Color.Black,
                    fontFamily = modernFamily,
                    fontSize = 28)
                CustomText(
                    text = "Skip",
                    textColor = Color.Gray,
                    fontFamily = modernFamily,
                    fontSize = 22,
                    onClick = onSkipClick
                    )
            }
//            Spacer(modifier = modifier.height(75.dp))
            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Image(
                    painter = painterResource(id = page.image),
                    contentDescription = null)
            }
            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Column (
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomText(
                        text = page.title,
                        textColor = Color.Black,
                        fontFamily = modernFamily,
                        fontSize = 40)
                    CustomText(
                        text = page.desc,
                        textColor = Color.Gray,
                        fontFamily = mohaveFamily,
                        fontSize = 20,
                        align = TextAlign.Center)
                }
            }
//            Spacer(modifier = modifier.height(125.dp))
            Row (
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PageIndicator(pageSize = 3, pageIndex = pageIndex)
                IconButton(
                    onClick = if (pageIndex != 2) onNextClick else onSkipClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        tint = Color(0xffF3725E),
                        contentDescription = null)
                }
            }
        }
    }
}


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    onFocusColor: Color = Color(0xffF3725E),
    onBackgroundColor: Color = Color(0xffEDBCB5),
    pageIndex: Int
) {
    Row (
        modifier = modifier.padding(top = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat (pageSize) {
            Box(
                modifier = modifier
                    .size(12.dp)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (pageIndex == it) onFocusColor else onBackgroundColor
                    )
            )
        }
    }
}

//val backgroundColor: Long = Color(0xffF3725E).toArgb().toLong()

@Preview (showBackground = true,
)
@Composable
fun PreviewPageIndicator () {
    PageIndicator(
        pageSize = 3,
        pageIndex = 0
    )
}

@Preview (showBackground = true,
    backgroundColor = 4292894014)
@Composable
fun PreviewOnBoardingPage() {
    OnBoardingPage(
        page = onBoardingPages[0],
        onNextClick = { /*TODO*/ },
        onSkipClick = { /*TODO*/ },
        pageIndex = 0
    )
}