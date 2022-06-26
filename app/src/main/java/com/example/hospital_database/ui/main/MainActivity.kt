package com.example.hospital_database.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.hospital_database.R
import com.example.hospital_database.ui.main.main_pager.MainPagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewPager = findViewById<ViewPager2>(R.id.main_viewpager)
        val viewPagerAdapter = MainPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
    }
}