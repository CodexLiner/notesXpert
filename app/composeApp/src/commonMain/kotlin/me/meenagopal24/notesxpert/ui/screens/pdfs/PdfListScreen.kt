package me.meenagopal24.notesxpert.ui.screens.pdfs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import me.meenagopal24.notesxpert.model.PdfItem
import me.meenagopal24.notesxpert.ui.getRandomColor
import me.meenagopal24.notesxpert.ui.navigation.Screens
import me.meenagopal24.notesxpert.utils.urlEncode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfListScreen(
    navController: NavHostController,
) {
    val pdfList = listOf(
        PdfItem(
            "https://qa.pilloo.ai/GeneratedPDF/Companies/202/2025-2026/DL.pdf",
            title = "Assignment Pdf"
        ),
        PdfItem(
            "https://drive.google.com/file/d/1HT872G8KWwp2usxe1QvKVpBiqFe1Tg_K/view?usp=sharing",
            title = "Please Open this to know me"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PDF List") }
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                items = pdfList,
                key = { it.url }
            ) { pdfItem ->
                PdfCard(
                    pdfItem = pdfItem,
                    onClick = {
                        navController.navigate(Screens.PdfViewScreen.createRoute(pdfItem.url.urlEncode() , pdfItem.title))
                    }
                )
            }
        }
    }
}

@Composable
private fun PdfCard(
    pdfItem: PdfItem,
    onClick: () -> Unit,
) {
    val cardColor by remember { mutableStateOf(getRandomColor()) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = pdfItem.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            if (pdfItem.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = pdfItem.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
        }
    }
}
