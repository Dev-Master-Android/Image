package com.example.image

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.image.ui.theme.ImageTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CharacterBuilder()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterBuilderPreview() {
    ImageTheme {
        CharacterBuilder()
    }
}