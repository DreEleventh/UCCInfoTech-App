package com.example.uccinfotech

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.uccinfotech.databinding.ActivityAdmissionsBinding
import com.google.android.material.navigation.NavigationView

class Admissions : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityAdmissionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdmissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.id.admissionsLayout)

        val drawerLayout = findViewById<DrawerLayout>(R.id.admissionsDrawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        val navView = findViewById<NavigationView>(R.id.navView)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.staffDirectoryView -> {
                    val intent = Intent(this, StaffDirectory::class.java)
                    startActivity(intent)
                }

                R.id.coursesView -> {
                    val intent = Intent(this, Courses::class.java)
                    startActivity(intent)
                }

                R.id.admissionsView -> {
                    val intent = Intent(this, Admissions::class.java)
                    startActivity(intent)
                }

                R.id.socialMediaView -> {
                    val intent = Intent(this, SocialMedia::class.java)
                    startActivity(intent)
                }
            }
//            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        binding.applyNowBtn.setOnClickListener { gotoAdmissionsPage() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun gotoAdmissionsPage() {
        val url = "https://ucc.edu.jm/apply/undergraduate/preform"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}