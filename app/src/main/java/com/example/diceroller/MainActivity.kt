package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var dice1 by remember { mutableIntStateOf(1) }
    var dice2 by remember { mutableIntStateOf(1) }

    // Efek animasi perubahan nilai dadu
    val animatedDice1 by animateIntAsState(
        targetValue = dice1,
        animationSpec = tween(durationMillis = 500) // Animasi dalam 500ms
    )
    val animatedDice2 by animateIntAsState(
        targetValue = dice2,
        animationSpec = tween(durationMillis = 500)
    )

    val dice1Image = when (animatedDice1) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val dice2Image = when (animatedDice2) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFE3919F), // Pink
            Color(0xFFF2C06D)  // Peach
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
            .wrapContentSize(Alignment.Center)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                AnimatedContent(targetState = animatedDice1) { target ->
                    Image(
                        painter = painterResource(dice1Image),
                        contentDescription = target.toString()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                AnimatedContent(targetState = animatedDice2) { target ->
                    Image(
                        painter = painterResource(dice2Image),
                        contentDescription = target.toString()
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Total: ${dice1 + dice2}",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    dice1 = (1..6).random()
                    dice2 = (1..6).random()
                },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD9A15B), // Peach Gelap
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.roll))
            }
        }
    }
}


