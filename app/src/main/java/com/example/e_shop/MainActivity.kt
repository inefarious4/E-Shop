package com.example.e_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.e_shop.ui.theme.EShopTheme
import com.example.e_shop.ui.theme.buttonTextStyle

data class Item(val id: Int, val title: String, val description: String, val image: Int, val price: Double)

val items = listOf(
    Item(1, "Yoasobi - Asia Tour 2024-2025", "Tanggal 21-06-2025", R.drawable.c1, 100.0),
    Item(2, "Baby Monster - HELLO MONSTER", "Tanggal 02-07-2025", R.drawable.c2, 150.0),
    Item(3, "Kyuhyun - COLORS", "Tanggal 18-07-2025", R.drawable.c3, 90.0),
)

fun getItemById(itemId: Int): Item? {
    return items.find { it.id == itemId }
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EShopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.logo), // Ganti dengan logo aplikasi kamu
                                            contentDescription = "Logo Aplikasi",
                                            modifier = Modifier.size(125.dp)
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "list",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("list") { ItemListScreen(navController) }
                            composable(
                                "detail/{itemId}",
                                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
                            ) { backStackEntry ->
                                val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
                                ItemDetailScreen(navController, itemId)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemListScreen(navController: NavController) {
    LazyColumn {
        items(items) { item ->
            ItemCard(item = item) {
                navController.navigate("detail/${item.id}")
            }
        }
    }
}

@Composable
fun ItemCard(item: Item, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = "Gambar ${item.title}",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = item.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemListScreenPreview() {
    EShopTheme {
        val navController = rememberNavController()
        ItemListScreen(navController = navController)
    }
}