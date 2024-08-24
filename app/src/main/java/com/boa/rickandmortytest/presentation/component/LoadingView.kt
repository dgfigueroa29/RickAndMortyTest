package com.boa.rickandmortytest.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingView(isLoading: State<Boolean>, onDismissRequest: () -> Unit) {
    if (isLoading.value) {
        // Dialog function
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            // Use a Box to control the layout more easily
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f)), // Add a semi-transparent background
                contentAlignment = Alignment.Center
            ) {
                // Use a Card to give the loading content a nice container
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .widthIn(max = 300.dp), // Limit the width for better visual appeal
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Replace the launcher icon with a more generic loading icon
                        Icon(
                            imageVector = Icons.Default.Refresh, // Example, choose a suitable icon
                            contentDescription = "Loading",
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Loading...",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium, // Use a more appropriate text style
                            color = MaterialTheme.colorScheme.primary,
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Please wait...",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}