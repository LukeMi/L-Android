package com.lukemi.android.tutorial.kt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.lukemi.android.tutorial.R


class Main1Activity : AppCompatActivity() {

//    var tv : TextView ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        findViewById<TextView>(R.id.tv_title).text = "title";
    }

    override fun onResume() {
        super.onResume();
    }


}
