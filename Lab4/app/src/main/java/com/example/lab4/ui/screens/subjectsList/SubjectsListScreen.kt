package com.example.lab4.ui.screens.subjectsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab4.data.db.DatabaseStorage
import com.example.lab4.data.entity.SubjectEntity
import com.lab4.ui.theme.Lab4Theme

@Composable
fun SubjectsListScreen(
    onDetailsScreen: (Int) -> Unit,
) {
    // Context - object which contains info about your app, has access to storage
    // is used for Room DB initialization
    val context = LocalContext.current
    // Getting the DB instance for screen
    val db = DatabaseStorage.getDatabase(context)
    val subjectsListState = remember { mutableStateOf<List<SubjectEntity>>(emptyList()) }

    /** ! Important !
        LaunchEffect(){...} - is the side effect which allows make operations in another thread (for accessing to DB)
     *  This example LaunchEffect(Unit) - means that the operations inside will be performed only once on creating the screen
     */
    LaunchedEffect(Unit) {
        // fetching the Subject from DB (subjectsDao is used)
        subjectsListState.value = db.subjectsDao.getAllSubjects()
    }

    // UI of screen
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(subjectsListState.value) {
                Text(
                    text = it.title,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onDetailsScreen(it.id) }
                )
            }
        }

    }
}

/**
 * Preview can't display data from DB
 */
@Preview(showBackground = true)
@Composable
private fun SubjectsListScreenPreview() {
    Lab4Theme {
        SubjectsListScreen({})
    }
}