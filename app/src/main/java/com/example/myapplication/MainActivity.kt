package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.maps.android.compose.GoogleMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val api = remember(::api)

            MyApplicationTheme {
                NavHost(
                    navController = navController,
                    startDestination = "splash",
                    builder = {
                        composable("splash"){

                            var imageVisisblity by remember { mutableStateOf(false) }

                            LaunchedEffect(key1 = Unit, block = {
                                delay(2000L)
                                imageVisisblity = true
                                val response = api.getMenu()
                                if(response.isSuccessful)
                                    navController.navigate("auth")
                            })

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color(0xFFFA4A0C),
                                                Color(0xFF7E6262),
                                            ),
                                        ),
                                    ),
                            ) {
                                Column {

                                    Row(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        AnimatedVisibility(visible = imageVisisblity) {
                                            Column(
                                                modifier = Modifier.fillMaxHeight(),
                                                verticalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text(
                                                    text = "WSR Food",
                                                    modifier = Modifier.padding(start = 40.dp, top = 50.dp),
                                                    fontSize = 70.sp,
                                                    fontStyle = FontStyle.Italic,
                                                    fontFamily = FontFamily.Cursive,
                                                    color = Color.White
                                                )

                                                Image(
                                                    painter = painterResource(id = R.drawable.cooking_2),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(250.dp)
                                                        .padding(start = 10.dp, bottom = 30.dp)
                                                )
                                            }
                                        }

                                        Column(
                                            modifier = if(imageVisisblity)
                                                Modifier
                                            else
                                                Modifier.fillMaxSize(),
                                            verticalArrangement = Arrangement.Bottom,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.cooking),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(250.dp)
                                                    .padding(10.dp)
                                            )

                                            Row {
                                                Image(
                                                    painter = painterResource(id = R.drawable.rectangle),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(100.dp)
                                                        .padding(10.dp)
                                                )

                                                Image(
                                                    painter = painterResource(id = R.drawable.rectangle),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(100.dp)
                                                        .padding(10.dp)
                                                )
                                            }
                                        }

                                        AnimatedVisibility(visible = imageVisisblity) {
                                            Column {
                                                Image(
                                                    painter = painterResource(id = R.drawable.cooking_3),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(200.dp)
                                                        .padding(end = 10.dp, bottom = 30.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        composable("auth"){

                            var email by remember { mutableStateOf("") }
                            var password by remember { mutableStateOf("") }
                            var errorText by remember { mutableStateOf("") }

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color(0xFFFA4A0C),
                                                Color(0xFF7E6262),
                                            ),
                                        ),
                                    ),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Column {
                                        Text(
                                            text = "WSR Food",
                                            modifier = Modifier.padding(start = 40.dp, top = 50.dp),
                                            fontSize = 70.sp,
                                            fontStyle = FontStyle.Italic,
                                            fontFamily = FontFamily.Cursive,
                                            color = Color.White
                                        )

                                        Spacer(modifier = Modifier.height(50.dp))


                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            if(errorText.isNotEmpty()){
                                                Text(
                                                    text = errorText,
                                                    color = Color.Red,
                                                    modifier = Modifier.padding(5.dp)
                                                )
                                            }

                                            TextField(
                                                value = email,
                                                onValueChange = { email = it },
                                                modifier = Modifier.padding(5.dp),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.Transparent,
                                                    focusedLabelColor = Color.Black,
                                                    focusedIndicatorColor = Color.Black,
                                                    cursorColor = Color.Black,
                                                    textColor = Color.Black
                                                ),
                                                label = {
                                                    Text(text = "Email", color = Color.Black)
                                                }
                                            )

                                            TextField(
                                                value = password,
                                                onValueChange = { password = it },
                                                modifier = Modifier.padding(5.dp),
                                                visualTransformation = PasswordVisualTransformation(),
                                                colors = TextFieldDefaults.textFieldColors(
                                                    backgroundColor = Color.Transparent,
                                                    focusedLabelColor = Color.Black,
                                                    focusedIndicatorColor = Color.Black,
                                                    cursorColor = Color.Black,
                                                    textColor = Color.Black
                                                ),
                                                label = {
                                                    Text(text = "Password", color = Color.Black)
                                                }
                                            )
                                            Text(
                                                text = "Forgot password?",
                                                color = Color.White,
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .align(Alignment.Start)
                                            )

                                            Button(
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .width(250.dp),
                                                shape = AbsoluteRoundedCornerShape(15.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    backgroundColor = Color(0xFFFA4A0C)
                                                ),
                                                onClick = {
                                                    scope.launch {
                                                        try {
                                                            val response = api.auth(AuthBody(email, password))

                                                            if(response.isSuccessful){
                                                                navController.navigate("maps")
                                                            }else {
                                                                errorText = response.code().toString()
                                                            }
                                                        }catch (e:Exception){
                                                            errorText = e.message.toString()
                                                        }
                                                    }
                                                }
                                            ) {
                                                Text(text = "Login", color = Color.White)
                                            }
                                        }
                                    }
                                    
                                    Spacer(modifier = Modifier.width(100.dp))

                                    Image(
                                        painter = painterResource(id = R.drawable.cooking),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(250.dp)
                                            .padding(10.dp)
                                    )
                                }
                            }
                        }

                        composable("maps"){
                            Row {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(300.dp)
                                        .background(Color(0xFFDDDDDD))
                                ) {
                                    LazyColumn {
                                        items(10){
                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(5.dp),
                                                shape = AbsoluteRoundedCornerShape(10.dp),
                                                backgroundColor = Color(0xFFFF9D9D)
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(
                                                        text = "Courier №$it",
                                                        color = Color.Black,
                                                        modifier = Modifier.padding(
                                                            start = 5.dp,
                                                            top = 15.dp,
                                                            bottom = 15.dp
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }

                                GoogleMap(modifier = Modifier.fillMaxSize())
                            }
                        }
                    }
                )
            }
        }
    }
}