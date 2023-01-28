package br.com.schmidt.modulecomposestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.schmidt.modulecomposestudy.ui.theme.ModuleComposeStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        ProfileCard()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
    elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.wrapContentSize()) {
            ProfilePicture()
            ProfileContent()
        }
    }
}

@Composable
fun ProfileContent() {

}

@Composable
fun ProfilePicture() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModuleComposeStudyTheme {
        MainScreen()
    }
}