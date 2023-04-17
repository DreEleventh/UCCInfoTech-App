package com.example.uccinfotech

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.uccinfotech.databinding.ActivityAdmissionsBinding
import com.google.android.material.navigation.NavigationView

class Admissions : AppCompatActivity() {

    private lateinit var binding: ActivityAdmissionsBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdmissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.id.admissionsLayout)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.open, R.string.close
        )

        drawerLayout.addDrawerListener(toggle)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.staffDirectoryView -> {
                    val intent = Intent(this, StaffDirectory::class.java)
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        binding.applyNowBtn.setOnClickListener { gotoAdmissionsPage() }
    }

    private fun gotoAdmissionsPage() {
        val url = "https://ucc.edu.jm/apply/undergraduate/preform"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}