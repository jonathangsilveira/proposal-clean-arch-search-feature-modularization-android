package com.jgsilveira.cleanarch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.jgsilveira.cleanarch.feature_a.impl.FeatureAActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()
        setContent {
            SearchCleanArchModularizedByFeatureAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/
        GlobalScope.launch {
            delay(500)
            val intent = FeatureAActivity.newIntent(this@MainActivity)
            startActivity(intent)
        }
    }
}
