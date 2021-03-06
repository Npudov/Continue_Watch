package com.example.second_lab

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2: AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences
    var isWork: Boolean = true


    var backgroundThread = Thread {
        while (true) {
            if (isWork) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds, secondsElapsed++)
                }
            }
            Thread.sleep(1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences(SECONDS, Context.MODE_PRIVATE)
        backgroundThread.start()

    }
    override fun onStart() {
        isWork = true
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        super.onStart()
    }

    override fun onStop() {
        isWork = false
        with(sharedPref.edit()) {
            putInt(SECONDS, secondsElapsed)
            apply()
        }
        super.onStop()
    }

    override fun onDestroy() {
        isWork = false
        with(sharedPref.edit()) {
            putInt(SECONDS, secondsElapsed)
            apply()
        }
        super.onDestroy()
    }

    companion object {
        const val SECONDS = "seconds"
    }
}