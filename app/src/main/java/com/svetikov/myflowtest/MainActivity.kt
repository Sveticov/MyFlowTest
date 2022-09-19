package com.svetikov.myflowtest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.svetikov.myflowtest.ui.theme.MyFlowTestTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFlowTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyFlowTest()
                }
            }
        }
    }
}

@Composable
fun MyFlowTest(model: MyFlowModel = viewModel()) {
    var timeLocal by remember {
        mutableStateOf(0)
    }
    var txt by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(20.dp)) {

            val time = model.time.collectAsState(initial = 10)
            OutlinedTextField(value =txt, onValueChange ={txt = it})

            Button(onClick = {

                scope.launch {
                    model.getTime(txt.toInt()).collect { timeLocal = it }//.filter { it>5 }.collect { it }
                    Log.d("Time", "Button push scope ${Thread.currentThread().name}")
                }

                Log.d("Time", "Button push")
            }) {
                Text(text = "ok")
            }
            Text(text = "${time.value}")
            Text(text = "$timeLocal")
        }
    }
}