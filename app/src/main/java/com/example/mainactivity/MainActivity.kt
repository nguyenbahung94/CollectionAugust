package com.example.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mainactivity.searchdogs.main.view.SearchDogsMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        event()
    }

    private fun event() {
        btnSearchDog.setOnClickListener {
            startActivity(Intent(this, SearchDogsMainActivity::class.java))
        }
    }
}