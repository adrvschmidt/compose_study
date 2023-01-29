package br.com.schmidt.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.schmidt.composestudy.ui.theme.ModuleComposeStudyTheme
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModuleComposeStudyTheme {
                UsersApplication()
            }
        }
    }
}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            MainScreen(userProfiles, navController)
        }
        composable(route = "users_details/{userId}", arguments = listOf(navArgument("userId") {
            type = NavType.IntType
        })) { navBackStackEntry ->
            UserProfileDetailScreen(navBackStackEntry.arguments!!.getInt("userId"), navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(userProfiles: List<UserProfile>, navController: NavController?) {
    Scaffold(topBar = {
        AppBar(title = "Users List", icon = Icons.Default.Home) {}
    },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                LazyColumn {
                    items(userProfiles.size) { index ->
                        ProfileCard(userProfiles[index]) {
                            navController?.navigate("users_details/${userProfiles[index].id}")
                        }
                    }
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { iconClickAction.invoke() }) {
                Icon(icon, contentDescription = null)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction.invoke() },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.drawableId, userProfile.status, 75.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfileContent(name: String, status: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        Text(
            name,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = if (status)
                "Active Row" else "Offline",
            style = MaterialTheme.typography.bodySmall,
            color = if (status) Color.Black.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.5f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePicture(drawableId: Int, status: Boolean, imageSize: Dp) {
    val imageModifier = Modifier
        .size(imageSize)
        .background(Color.LightGray)
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = if (status) Color.Green else Color.Red),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileDetailScreen(userId: Int, navController: NavController?) {
    val userProfile = userProfileList.first { userProfile -> userId == userProfile.id }
    Scaffold(topBar = {
        AppBar(
            title = "${userProfile.name} Details",
            icon = Icons.Default.ArrowBack
        ) {
            navController!!.navigateUp()
        }
    }, content = {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(userProfile.drawableId, userProfile.status, 200.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun UserDetailtPreview() {
    ModuleComposeStudyTheme {
        UserProfileDetailScreen(1, null)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModuleComposeStudyTheme {
        MainScreen(userProfiles = userProfileList, null)
    }
}