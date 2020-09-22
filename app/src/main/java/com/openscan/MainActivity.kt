package com.openscan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.monscanner.ScanActivity
import com.monscanner.ScanConstants
import com.openscan.ui.fragments.HomeFragment
import com.openscan.ui.fragments.OCRFragment
import com.openscan.ui.fragments.QRFragment
import java.io.FileNotFoundException
import java.util.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 7
    private var imageView: ImageView? = null
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_nav_view)
        loadFragment(HomeFragment())
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home ->
                {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.ocr ->
                {
                    loadFragment(OCRFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.qr ->
                {
                    loadFragment(QRFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else ->
                {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

        setupToolbar()
    }

    private fun setupToolbar() {
        var toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        toolbar.setTitle("ScanIt")
        toolbar.setTitleTextAppearance(applicationContext, R.style.TextAppearance_AppCompat_Title)
        toolbar.setTitleTextColor(-0x1)
        setSupportActionBar(toolbar)
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}