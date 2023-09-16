package com.jetcom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetcom.ui.theme.JetcomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetcomTheme {
                // A surface container using the 'background' color from the theme
                startApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun startApp(modifier: Modifier = Modifier) {
    var showOnboarding by rememberSaveable{ mutableStateOf(true) }
    Surface(modifier) {
        if (showOnboarding) {
            OnboardingScreen(onContinueClicked =  { showOnboarding = false})
        } else {
            MyApp()
        }
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {
    var showOnboarding by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Welcome to onboarding screen")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { showOnboarding = false }) {
            Text(text = "Continue")
        }
    }

}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreView() {
    JetcomTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val names: List<String> = List(1000) { "$it" }
    LazyColumn(modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(8.dp, 4.dp)) {
        Row(modifier = Modifier.padding(if (expanded) 48.dp else 24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = if (expanded) 24.dp else 0.dp)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    JetcomTheme {
        MyApp()
    }
}