package com.android.fastestdeliverywsr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomScreen(
    navController: NavController
) {
    val pageState = rememberPagerState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFA4A0C)
    ) {
        HorizontalPager(count = 2, state = pageState) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when(it){
                    0 -> Page(pagerState = pageState)
                    1 -> Page1(
                        pagerState = pageState,
                        navController = navController
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Page(
    pagerState: PagerState
) {
    Image(
        painter = painterResource(id = R.drawable.ililustration),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.65f)
    )

    Text(
        text = "Fastest Delivery\n24/7",
        fontSize = 38.sp,
        modifier = Modifier.padding(5.dp),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(40.dp))

    HorizontalPagerIndicator(
        pagerState = pagerState,
        activeColor = Color.White,
        modifier = Modifier.padding(5.dp)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Page1(
    pagerState: PagerState,
    navController: NavController
) {
    Image(
        painter = painterResource(id = R.drawable.group),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    )

    Spacer(modifier = Modifier.height(30.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { navController.navigate("auth") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = AbsoluteRoundedCornerShape(20.dp),
            modifier = Modifier.size(120.dp, 60.dp)
        ) {
            Text(
                text = "Sign In",
                fontWeight = FontWeight.W900,
                modifier = Modifier.padding(5.dp),
                fontFamily = FontFamily.Cursive,
                color = Color.Black
            )
        }

        Button(
            onClick = { navController.navigate("reg") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = AbsoluteRoundedCornerShape(20.dp),
            modifier = Modifier.size(120.dp, 60.dp)
        ) {
            Text(
                text = "Sign Up",
                fontWeight = FontWeight.W900,
                modifier = Modifier.padding(5.dp),
                fontFamily = FontFamily.Cursive,
                color = Color.Black
            )
        }
    }

    Spacer(modifier = Modifier.height(40.dp))

    HorizontalPagerIndicator(
        pagerState = pagerState,
        activeColor = Color.White,
        modifier = Modifier.padding(5.dp)
    )

    TextButton(
        modifier = Modifier.padding(5.dp),
        onClick = { navController.navigate("main_screen") }
    ) {
        Text(
            text = "Skip Authorization",
            color = Color.Black
        )
    }
}