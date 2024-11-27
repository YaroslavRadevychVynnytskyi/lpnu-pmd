package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab2.ui.theme.Lab2Theme

/**
 * MainActivity
 * - entrypoint of application (extends ComponentActivity() which allows Compose)
 * - starts the compose UI by setContent{}
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                MainActivityScreen()
            }
        }
    }
}

@Composable
fun MainActivityScreen() {
    val itemList = remember { mutableStateListOf<Item>() }
    val textFieldId = remember { mutableStateOf("") }
    val textFieldName = remember { mutableStateOf("") }
    val textFieldDescription = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(7f)
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(itemList) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "ID: ${item.id}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = item.name, style = MaterialTheme.typography.bodyMedium)
                        Text(
                            text = item.description ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                    IconButton(onClick = { itemList.remove(item) }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_delete),
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }

        // Input Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
                .background(Color.Yellow)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = textFieldId.value,
                onValueChange = { textFieldId.value = it },
                label = { Text("Enter ID") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = textFieldName.value,
                onValueChange = { textFieldName.value = it },
                label = { Text("Enter Name") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = textFieldDescription.value,
                onValueChange = { textFieldDescription.value = it },
                label = { Text("Enter Description (optional)") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (textFieldId.value.isNotBlank() && textFieldName.value.isNotBlank()) {
                        itemList.add(
                            Item(
                                id = textFieldId.value,
                                name = textFieldName.value,
                                description = textFieldDescription.value.takeIf { it.isNotBlank() }
                            )
                        )
                        textFieldId.value = ""
                        textFieldName.value = ""
                        textFieldDescription.value = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = textFieldId.value.isNotBlank() && textFieldName.value.isNotBlank()
            ) {
                Text(text = "Add Item")
            }
        }
    }
}

data class Item(val id: String, val name: String, val description: String? = null)

@Preview
@Composable
fun MainActivityPreview() {
    Lab2Theme {
        MainActivityScreen()
    }
}
