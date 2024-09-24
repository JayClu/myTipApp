package hoangloc.mytipapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hoangloc.mytipapp.R

@Composable
fun loginscreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }
   var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logorest),
            contentDescription = "restaurant logo"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Welcome !",
            fontSize = 35.sp, fontWeight = FontWeight.Bold)
        Text(text = "Sign in to continue",
            fontSize = 20.sp, fontWeight = FontWeight.Light)

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = email, onValueChange = {
            email = it
        },
            label = { Text(text = "Email Address")})

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = password, onValueChange = {
            password = it
        },
            label = { Text(text = "Password")})

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Forgot Password?",
            fontSize = 15.sp, fontWeight = FontWeight.Light,
            modifier = Modifier.clickable {  })

        Button(onClick ={
          navController.navigate("maintip")
        }) {
            Text(text = "Login", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
    }
}