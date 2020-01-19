package com.and.game.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        alertLoading.setOnClickListener {
            startActivity(Intent(this@MainActivity,GameActivity::class.java))
        }
    }
}
