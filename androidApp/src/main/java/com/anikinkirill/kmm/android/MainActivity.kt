package com.anikinkirill.kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anikinkirill.kmm.AndroidHttpClient
import com.anikinkirill.kmm.android.users.UserFormatter
import com.anikinkirill.kmm.android.users.UserViewModel
import com.anikinkirill.kmm.android.users.UserViewObject
import com.anikinkirill.kmm.data.mapper.UserMapper
import com.anikinkirill.kmm.data.repository.UserRepositoryImpl
import com.anikinkirill.kmm.data.service.UserServiceImpl
import com.anikinkirill.kmm.domain.usecase.GetUsersUseCase

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = UserServiceImpl(AndroidHttpClient())
        val userRepositoryImpl = UserRepositoryImpl(service, UserMapper())
        val getUsersUseCase = GetUsersUseCase(userRepositoryImpl)
        val viewModel = UserViewModel(getUsersUseCase, UserFormatter())

        setContent {
            val screenState by viewModel.screenState.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.loadUsers()
            }

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UsersList(screenState.users)
                }
            }
        }
    }
}

@Composable
fun UsersList(users: List<UserViewObject>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users) { userViewObject ->
            UserRowItem(userViewObject = userViewObject)
        }
    }
}

@Composable
fun UserRowItem(userViewObject: UserViewObject) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = userViewObject.name)
            Text(text = userViewObject.username)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        UsersList(users = emptyList())
    }
}
