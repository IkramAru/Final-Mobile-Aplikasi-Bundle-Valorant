package com.d121211104.bundle.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211104.bundle.data.models.Data
import com.d121211104.bundle.ui.theme.AplikasiMobileBundleValorantTheme

class DetailActivity : ComponentActivity() {

    private var selectedBundle: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedBundle = intent.getParcelableExtra("Bundle")
        setContent {
            AplikasiMobileBundleValorantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen()
                }
            }
        }
    }


    @Composable
    private fun DetailScreen() {
        LazyColumn {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(selectedBundle?.displayIcon)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = selectedBundle?.displayName.toString())
                Text(text = selectedBundle?.description.toString())
            }
        }
    }

}