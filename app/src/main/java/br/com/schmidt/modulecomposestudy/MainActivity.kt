package br.com.schmidt.modulecomposestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.schmidt.modulecomposestudy.ui.theme.ModuleComposeStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModuleComposeStudyTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(topBar = { AppBar() } , content = {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Column {
                userProfileList.forEach { userProfile ->
                    ProfileCard(userProfile)
                }
            }
        }
    })
}

@Composable
fun AppBar(){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(),
        title = { Text("TopAppBar") },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(8.dp),
        colors =  CardDefaults.cardColors(
            containerColor =  Color.White,
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.drawableId, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfileContent(name: String, status: Boolean) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            name,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = if(status)
            "Active Row" else "Offline",
            style = MaterialTheme.typography.bodySmall,
            color = if(status) Color.Black.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.5f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePicture(drawableId: Int, status: Boolean) {
    val imageModifier = Modifier
        .size(75.dp)
        .background(Color.LightGray)
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = if(status) Color.Green else Color.Red),
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "teste",
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModuleComposeStudyTheme {
        MainScreen()
    }
}