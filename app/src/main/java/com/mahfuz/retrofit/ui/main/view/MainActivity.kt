package com.mahfuz.retrofit.ui.view

import CommonBlackTextStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahfuz.retrofit.data.model.Post
import com.mahfuz.retrofit.ui.main.viewmodel.MainViewModel
import com.mahfuz.retrofit.ui.theme.RetrofitTheme
import com.mahfuz.retrofit.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Top app bar", style = CommonBlackTextStyle)
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        GetData(mainViewModel = mainViewModel)
                    }
                    /* Greeting(
                         name = "Android",
                         modifier = Modifier.padding(innerPadding)
                     )*/
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun EachRow(post: Post) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = post.body, modifier = Modifier.padding(10.dp), fontStyle = FontStyle.Italic)
    }
}

@Composable
fun GetData(mainViewModel: MainViewModel) {
    when (val result = mainViewModel.response.value) {
        is ApiState.Success -> {
            LazyColumn {
                items(result.data) { response ->
                    EachRow(response)
                }
            }
        }

        is ApiState.Failure -> {
            Text(text = "${result.msg}")
        }

        ApiState.Loading -> {
            CircularProgressIndicator()
        }

        ApiState.Empty -> {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitTheme {
        Greeting("Android")
    }
}