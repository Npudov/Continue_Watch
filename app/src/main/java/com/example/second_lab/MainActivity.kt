package com.example.second_lab


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private val TAG = "States"


    var isSee = true

    var backgroundThread = Thread {
        while (true) {
            if (isSee) {
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds, secondsElapsed++)
                }
                Thread.sleep(1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
        Log.i(TAG, "MainActivity: onCreate()")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(SECONDS, secondsElapsed)
        }
        super.onSaveInstanceState(outState)
        Log.i(TAG, "MainActivity: onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)


        savedInstanceState.run {
            secondsElapsed = getInt(SECONDS)
        }
        Log.i(TAG, "MainActivity: onRestoreInstanceState()")
    }

    override fun onStart() {
        isSee = true
        super.onStart()
        Log.i(TAG, "MainActivity: onStart()")
    }

    override fun onStop() {
        isSee = false
        super.onStop()
        Log.i(TAG, "MainActivity: onStop()")
    }

    override fun onDestroy() {
        isSee = false
        super.onDestroy()
        Log.i(TAG, "MainActivity: onDestroy()")
    }

    companion object {
        const val SECONDS = "seconds"
    }

}