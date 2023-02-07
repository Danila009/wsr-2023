package com.android.fastestdeliverywsr

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.fastestdeliverywsr.data.api

@Composable
fun StartScreen(
    navController: NavController
) {
    val context = LocalContext.current

    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp

    val api = remember { api }

    LaunchedEffect(key1 = Unit, block = {
        val result = api.getMenu()

        if(result.isSuccessful){
            navController.navigate("welcom")
        }
    })

    Image(
        bitmap = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(context.resources, R.drawable.start),
            screenWidthDp,
            screenHeightDp,
            false
        ).asImageBitmap(),
        contentDescription = null,
        modifier = Modifier.size(width = screenWidthDp.dp, height = screenHeightDp.dp)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(0.7f),
            backgroundColor = Color(0x99FFFFFF),
            shape = CircleShape
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cooking),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(5.dp)
                )
                
                Text(
                    text = "WSR Food",
                    fontWeight = FontWeight.W900,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(5.dp),
                    fontFamily = FontFamily.Cursive
                )

                CircularProgressIndicator(
                    color = Color(0xFFFA4A0C),
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}