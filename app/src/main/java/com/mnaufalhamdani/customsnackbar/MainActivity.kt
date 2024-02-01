package com.mnaufalhamdani.customsnackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mnaufalhamdani.customsnackbar.ui.theme.CustomSnackbarTheme
import com.mnaufalhamdani.customsnackbarcompose.CustomSnackbarCompose
import com.mnaufalhamdani.customsnackbarcompose.R
import com.mnaufalhamdani.customsnackbarcompose.TypeMessage
import com.mnaufalhamdani.customsnackbarcompose.stateOfCustomSnackbar
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomSnackbarTheme {
                BuildContent()
            }
        }
    }

    @Composable
    private fun BuildContent() {
        var alertText by remember { mutableStateOf("Ini alert") }
        val customSnackState = stateOfCustomSnackbar()

        val snackState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        getString(R.string.app_name),
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nunitosans_bold)),
                    )
                }

                Button(
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.green_A700)),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    onClick = {
                        alertText = "Ini alert success"
                        customSnackState.showSnackbarMessage(
                            TypeMessage.SUCCESS,
                            alertText
                        )

                        coroutineScope.launch {
                            snackState.showSnackbar("Custom Snackbar")
                        }
                    }
                ) {
                    Text("Alert Success")
                }

                Button(
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.blue_A700)),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    onClick = {
                        alertText = "Ini alert info"
                        customSnackState.showSnackbarMessage(
                            TypeMessage.INFO,
                            alertText
                        )
                    }
                ) {
                    Text("Alert Info")
                }

                Button(
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.amber_A700)),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    onClick = {
                        alertText = "Ini alert warning"
                        customSnackState.showSnackbarMessage(
                            TypeMessage.WARNING,
                            alertText
                        )
                    }
                ) {
                    Text("Alert Warning")
                }

                Button(
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.red_A700)),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    onClick = {
                        alertText = "Ini alert error"
                        customSnackState.showSnackbarMessage(
                            TypeMessage.ERROR,
                            alertText
                        )
                    }
                ) {
                    Text("Alert Error")
                }
            }

            CustomSnackbarCompose(state = customSnackState)
        }
    }
}
