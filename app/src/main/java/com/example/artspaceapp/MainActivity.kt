package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme


data class ArtSpaceData(val imageResource: Int, val title: Int, val subtitle: Int)

val artSpaceData = listOf(
    ArtSpaceData(R.drawable.bholenath, R.string.title, R.string.subtitle),
    ArtSpaceData(R.drawable.peakpx, R.string.title, R.string.subtitle),
    ArtSpaceData(R.drawable.pexels_albert_h_3317974, R.string.title, R.string.subtitle)

)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    var currentQuotes by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        ArtWork(imageResource = artSpaceData[currentQuotes].imageResource)
        ArtDescriptor(
            title = artSpaceData[currentQuotes].title,
            subtitle = artSpaceData[currentQuotes].subtitle
        )
        DisplayControl(
            onPreviousClicked = {
                if (currentQuotes > 0) {
                    currentQuotes--
                }
            },
            onNextClicked = {
                if (currentQuotes < artSpaceData.size - 1)
                    currentQuotes++
            }
        )
    }
}

@Composable
fun ArtWork(
    imageResource: Int,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.shadow(10.dp)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "null",
            modifier = Modifier
                .size(350.dp)
                .padding(10.dp)
        )
    }
}

@Composable
fun ArtDescriptor(
    title: Int,
    subtitle: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = modifier.padding(top = 50.dp))
        Text(
            text = stringResource(title),
            modifier = Modifier.padding(top = 50.dp)
        )
        Text(
            text = stringResource(subtitle),
            modifier = modifier.padding(bottom = 40.dp)
        )
    }
}

@Composable
fun DisplayControl(
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Button(
            onClick = { onPreviousClicked() }
        ) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { onNextClicked() }) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtWorkPreview() {
    ArtSpaceAppTheme {
        ArtWork(
            imageResource = R.drawable.bholenath,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtDescriptorPreview() {
    ArtSpaceAppTheme {
        ArtDescriptor(
            title = R.string.title,
            subtitle = R.string.author
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayControlPreview() {
    ArtSpaceAppTheme {
        DisplayControl(onPreviousClicked = { }, onNextClicked = { })
    }
}

