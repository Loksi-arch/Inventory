package com.visionary_ventures.inventoryvision.ui.theme.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.visionary_ventures.inventoryvision.R
import com.visionary_ventures.inventoryvision.navigation.ROUTE_HOME
import com.visionary_ventures.inventoryvision.ui.theme.Mynewcolor
import com.visionary_ventures.inventoryvision.ui.theme.green
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Splash_Screen(navController: NavHostController) {

    val coroutine= rememberCoroutineScope()
    coroutine.launch {
        delay(3000)
        navController.navigate(ROUTE_HOME)
    }



    Column(modifier=Modifier
        .fillMaxSize()
        .background(Mynewcolor)
        .size(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(id = R.drawable.splash),
            contentDescription = "splash",
            modifier = Modifier
                .width(300.dp)
                .height(400.dp))
        Text("Your next adventure awaits",
            fontSize =20.sp,
            fontWeight = FontWeight.ExtraBold,
            color = green,
            fontFamily = FontFamily.Cursive,
        )


    }


}

@Preview
@Composable
private fun SplashPrev() {
    Splash_Screen(rememberNavController())

}