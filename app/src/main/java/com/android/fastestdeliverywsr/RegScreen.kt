package com.android.fastestdeliverywsr

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.fastestdeliverywsr.data.Reg
import com.android.fastestdeliverywsr.data.api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

@Composable
fun RegScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fio by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }


    val api = remember { api }
    var isSuccessful by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(key1 = isSuccessful, block = {
        isSuccessful?.let {
            if(it){
                navController.navigate("main_screen")
            }else {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }
        }
    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEAEAEA)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                shape = AbsoluteRoundedCornerShape(
                    bottomLeft = 20.dp,
                    bottomRight = 20.dp
                ),
                backgroundColor = Color(0xFFF3F3F3)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cooking),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "E-mail") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFEAEAEA)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFEAEAEA)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = fio,
                onValueChange = { fio = it },
                label = { Text(text = "Full name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFEAEAEA)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(text = "Phone number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFEAEAEA)
                )
            )

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        isSuccessful = try {
                            val response = api.reg(
                                Reg(
                                    email = email,
                                    password = password,
                                    phone = phone.toInt(),
                                    fio = fio
                                )
                            )

                            response.isSuccessful
                        }catch (e: Exception){
                            false
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFA4A0C)),
                shape = AbsoluteRoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "Register new",
                    modifier = Modifier.padding(5.dp),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = { navController.navigateUp() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFA4A0C)),
                shape = AbsoluteRoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "Cancel",
                    modifier = Modifier.padding(5.dp),
                    color = Color.White
                )
            }
        }
    }
}