package com.darksuntechnologies.tasks_aaftab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.darksuntechnologies.tasks_aaftab.adapters.PageAdapter
import com.google.android.material.tabs.TabLayout

class IntroActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var skipBtn: Button? = null
        var nextBtn: Button? = null
        var gotItBtn: Button? = null

        val viewPager = findViewById<ViewPager>(R.id.prodyct_images_viewpager)
        val tabLayout = findViewById<TabLayout>(R.id.viewPager_Indicator)

        nextBtn = findViewById(R.id.nextBtn)
        gotItBtn = findViewById(R.id.gotitBtn)
        skipBtn = findViewById(R.id.skipBtn)

        val adapter = PageAdapter(this)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == adapter.count - 1) {
                    // Last page reached, hide next button, show "Got It" button
                    nextBtn.visibility = View.GONE
                    skipBtn.visibility = View.GONE
                    gotItBtn.visibility = View.VISIBLE
                } else {
                    // Not the last page, show next button, hide "Got It" button
                    nextBtn.visibility = View.VISIBLE
                    skipBtn.visibility = View.VISIBLE
                    gotItBtn.visibility = View.GONE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        nextBtn.setOnClickListener(View.OnClickListener {
            val nextPage = viewPager.currentItem + 1
            if (nextPage < adapter.count) {
                viewPager.currentItem = nextPage
            }
        })

        gotItBtn.setOnClickListener(View.OnClickListener { // Handle "Got It" button click action
            // For example, you can start the main activity here
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            finish()
        })


        skipBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
        })
    }
}