package com.example.myjetpackcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjetpackcomposeproject.ui.theme.MyJetpackComposeProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetpackComposeProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ItemInputForm()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemInputForm() {

    var itemName by remember {
        mutableStateOf("")
    }

    var items by remember {
        mutableStateOf(listOf<String>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = itemName,
                onValueChange = { text ->
                    itemName = text
                },
                singleLine = true,
                label = { Text(text = "Item name") },
                trailingIcon = {
                    if (itemName.isNotBlank()) {
                        IconButton(
                            onClick = {
                                itemName = ""
                            }
                        ) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = null
                            )
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = {
                    items = addItem(itemName, items)
                }
            ) {
                Text(
                    text = "Add"
                )
            }
        }

        DisplayItems(items)

    }

}

fun addItem(itemName: String, items: List<String>): List<String> {
    var newItems = items

    if (itemName.isNotBlank() && !items.contains(itemName))
        newItems = items + itemName

    return newItems
}

@Composable
fun DisplayItems(items: List<String>) {

    Spacer(modifier = Modifier.size(16.dp))
    LazyColumn {
        items(items) { currentItem ->
            Text(
                text = currentItem,
                modifier = Modifier
                    .padding(12.dp)
            )
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyJetpackComposeProjectTheme {
        ItemInputForm()
    }
}