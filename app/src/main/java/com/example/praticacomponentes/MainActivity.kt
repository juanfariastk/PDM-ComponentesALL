package com.example.praticacomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praticacomponentes.ui.theme.PraticaComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PraticaComponentesTheme {
                AppContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    val listItems = remember { mutableStateListOf<String>() }
    for (i in 0..9) {
        listItems.add("List item - $i")
    }

    var showDialog by remember { mutableStateOf(false) }
    var newListItem by remember { mutableStateOf("") }

    var showHomeCard by remember { mutableStateOf(false) }
    var showCreateCard by remember { mutableStateOf(false) }
    var showSettingsCard by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pratica Componentes Adocidados") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation */ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_menu_24),
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle search */ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_search_24),
                            contentDescription = "Search"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "Add"
                )
            }
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_home_24),
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") },
                    selected = false,
                    onClick = { showHomeCard = true }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_create_24),
                            contentDescription = "Create"
                        )
                    },
                    label = { Text("Create") },
                    selected = false,
                    onClick = { showCreateCard = true }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_settings_24),
                            contentDescription = "Settings"
                        )
                    },
                    label = { Text("Settings") },
                    selected = false,
                    onClick = { showSettingsCard = true }
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                items(listItems) { item ->
                    ListItem(
                        headlineContent = { Text(item) },
                        leadingContent = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_circle_24),
                                contentDescription = "Item icon"
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = { /* Handle navigate action */ },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_navigation_24),
                        contentDescription = "Navigate"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Navigate")
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Adicionar Item") },
                text = {
                    TextField(
                        value = newListItem,
                        onValueChange = { newListItem = it },
                        label = { Text("Novo Item") }
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        listItems.add(newListItem)
                        showDialog = false
                        newListItem = ""
                    }) {
                        Text("Adicionar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        if (showHomeCard) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(23.dp)
                    .padding(83.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding(23.dp)
                        .fillMaxWidth(),
                    onClick = { showHomeCard = false }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Home Açucarado", style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("Indo para a...")
                    }
                }
            }
        }

        if (showCreateCard) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(23.dp)
                    .padding(83.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding(23.dp)
                        .fillMaxWidth(),
                    onClick = { showCreateCard = false }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Create Adoçado", style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text("Botão errado amigo! Clique no botão flutuante para adicionar.")
                    }
                }
            }
        }

        if (showSettingsCard) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(23.dp)
                    .padding(83.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding(23.dp)
                        .fillMaxWidth(),
                    onClick = { showSettingsCard = false }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Settings Açucaradas", style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text("Configurações docinhas!")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PraticaComponentesTheme {
        AppContent()
    }
}