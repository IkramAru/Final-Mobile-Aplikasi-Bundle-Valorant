package com.d121211104.bundle.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211104.bundle.data.models.Data
import com.d121211104.bundle.ui.activities.detail.DetailActivity
import com.d121211104.bundle.ui.theme.AplikasiMobileBundleValorantTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplikasiMobileBundleValorantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "List of skin bundles") },
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = {}) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                            }
                        }
                    ) {
                        Column(modifier = Modifier.padding(it)) {
                            val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                            ListBundleScreen(mainViewModel.mainUiState)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ListBundleScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        when(mainUiState) {
            is MainUiState.Success -> ListBundle(mainUiState.bundles)
            is MainUiState.Error -> ErrorText()
            is MainUiState.Loading -> LoadingBar()
        }
    }

    @Composable
    private fun ErrorText() {
        Text(text = "ERROR")
    }

    @Composable
    fun LoadingBar() {
        Text(text = "SEDANG LOADING")
    }

    @Composable
    private fun ListBundle(bundles: List<Data> , modifier: Modifier = Modifier) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2)
        ) {
            items(bundles) { bundle ->
                BundleCard(bundle = bundle)
            }
        }
    }

    @Composable
    private fun BundleCard(bundle: Data, modifier: Modifier = Modifier) {
        Card(modifier = Modifier.clickable {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("Bundle", bundle.displayName)
            startActivity(intent)
        }) {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(bundle.displayIcon)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = bundle.displayName ?: "Title")
            }
        }
    }

}