package com.example.navigation_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            repeat(Integer.MAX_VALUE) {
                val fm = supportFragmentManager
                val count = fm.backStackEntryCount
                Toast.makeText(this@MainActivity, "count = $it", Toast.LENGTH_SHORT)
                    .show()
                val fragments = (0 until count)
                    .map(fm::getBackStackEntryAt)
                    .map(FragmentManager.BackStackEntry::getId)
                    .joinToString(",\n")
                textView.text = fragments
                delay(100L)
            }
        }
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}
