package vn.edu.hcmut.furniturestore.adapter.arview


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import vn.edu.hcmut.furniturestore.R
import vn.edu.hcmut.furniturestore.utils.constant.Constants

@Composable
internal fun GestureOverlay(
    modifier: Modifier = Modifier,
    iterations: Int = Constants.DEFAULT_GESTURE_OVERLAY_ITERATIONS,
    onComplete: () -> Unit
) {
    // Create lottie composition specs for all gestures
    val specs = listOf(
        R.raw.anim_move_gesture,
        R.raw.anim_rotate_gesture,
        R.raw.anim_zoom_gesture
    ).map(LottieCompositionSpec::RawRes)

    // Display messages for gesture
    val messages = listOf(
        R.string.message_move_gesture,
        R.string.message_rotate_gesture,
        R.string.message_zoom_gesture
    ).map { stringResource(it) }

    // Number of times the gestures are played
    var playedCount by rememberSaveable { mutableIntStateOf(Constants.PLAY_COUNT_ZERO) }

    // Current index of gesture
    val currentGestureIndex by remember(playedCount) {
        // If each gesture is already played for [iterations] times, call onComplete
        if (playedCount >= iterations * specs.size) {
            onComplete()
        }
        mutableIntStateOf(playedCount % specs.size)
    }

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        key(currentGestureIndex) {
            Text(
                text = messages[currentGestureIndex],
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            LottieView(compositionSpec = specs[currentGestureIndex]) {
                playedCount++
            }
        }
    }
}

@Composable
private fun LottieView(
    modifier: Modifier = Modifier,
    compositionSpec: LottieCompositionSpec,
    onEnd: () -> Unit
) {
    val composition by rememberLottieComposition(compositionSpec)
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        modifier = modifier.aspectRatio(1F),
        composition = composition,
        contentScale = ContentScale.FillWidth,
        progress = {
            if (progress == Constants.ANIM_COMPLETE_VALUE) {
                onEnd()
            }
            progress
        }
    )
}