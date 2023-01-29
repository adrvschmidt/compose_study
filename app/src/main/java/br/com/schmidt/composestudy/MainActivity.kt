package br.com.schmidt.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.schmidt.composestudy.ui.theme.ModuleComposeStudyTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

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
fun MainScreen(userProfiles: List<UserProfile> = userProfileList) {
    Scaffold(topBar = { AppBar() } , content = {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            LazyColumn {
                items(userProfiles.size) { index ->
                    ProfileCard(userProfiles[index])
                }
            }
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(){
    TopAppBar(
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
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(drawableId)
                .size(Size.ORIGINAL) // Set the target size to load the image at.
                .build()
        )
        Image(
            painter = painter,
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