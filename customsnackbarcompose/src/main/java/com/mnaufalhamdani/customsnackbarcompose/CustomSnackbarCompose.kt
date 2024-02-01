package com.mnaufalhamdani.customsnackbarcompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import java.util.Timer
import kotlin.concurrent.schedule

enum class TypeMessage {
    SUCCESS, ERROR, INFO, WARNING
}

@Composable
fun stateOfCustomSnackbar(): CustomSnackbarState {
    return remember { CustomSnackbarState() }
}

@Composable
fun CustomSnackbarCompose(
    state: CustomSnackbarState
) {
    val timer = Timer("Animation Timer", true)

    val duration = when(state.duration.value) {
        SnackbarDuration.Short -> 4000L
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Indefinite -> 360000L
    }

    val drawableRes = when(state.typeMessage.value) {
        TypeMessage.SUCCESS -> R.drawable.img_anim_success
        TypeMessage.INFO -> R.drawable.img_anim_info
        TypeMessage.WARNING -> R.drawable.img_anim_warning
        TypeMessage.ERROR -> R.drawable.img_anim_error
    }

    val colorRes = when(state.typeMessage.value) {
        TypeMessage.SUCCESS -> colorResource(R.color.green_A700)
        TypeMessage.INFO -> colorResource(R.color.blue_A700)
        TypeMessage.WARNING -> colorResource(R.color.amber_A700)
        TypeMessage.ERROR -> colorResource(R.color.red_A700)
    }

    DisposableEffect(
        key1 = state.updateState
    ) {
        timer.schedule(duration) {
            state.updateState = false
        }

        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AnimatedVisibility(
            visible = state.updateState,
            enter = slideInVertically(initialOffsetY = { it / 2 }),
            exit = slideOutVertically(targetOffsetY = { it / 2 }),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = state.paddingBottom.value.dp)
                    .background(Color.White, RoundedCornerShape(25.dp))
                    .border(1.dp, colorRes, RoundedCornerShape(25.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current).data(drawableRes).apply {
                        size(Size.ORIGINAL)
                    }.build(), loaderOfImage(context = LocalContext.current)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 50.dp, height = 50.dp)
                        .padding(start = 10.dp),
                )

                Column(
                    modifier = Modifier.padding(top = 15.dp, end = 15.dp, bottom = 15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Pemberitahuan",
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.nunitosans_bold))
                        )

                        IconButton(
                            modifier = Modifier.size(width = 20.dp, height = 20.dp),
                            onClick = {
                                state.updateState = false
                                timer.cancel()
                                timer.purge()
                            }
                        ) {
                            Icon(Icons.Filled.Close, contentDescription = null)
                        }
                    }
                    Text(
                        state.message.value,
                        color = colorResource(R.color.grey_700),
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.nunitosans_light)),
                        lineHeight = 12.sp
                    )
                }
            }
        }
    }
}
