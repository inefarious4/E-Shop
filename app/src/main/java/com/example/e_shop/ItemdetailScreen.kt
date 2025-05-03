package com.example.e_shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_shop.ui.theme.topAppBarTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailScreen(navController: NavController, itemId: Int) {
    val item = getItemById(itemId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Kembali ke List Ticket", style = topAppBarTextStyle) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                windowInsets = WindowInsets(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp),
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        if (item != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = "Gambar ${item.title}",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = item.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.description)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Harga: $${item.price}")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Tambahkan logika untuk membeli item
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Beli Ticket Sekarang")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        // Tambahkan logika untuk share item
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Share")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        // Tambahkan logika untuk melihat peta lokasi
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Lihat Tempat Duduk Tersedia")
                }
            }
        } else {
            Text(text = "Item tidak ditemukan")
        }
    }
}