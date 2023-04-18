package com.example.uccinfotech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.uccinfotech.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout = binding.drawerLayout
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
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
            true
        }

        binding.emailFab.setOnClickListener{emailHOD()}
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun emailHOD() {
        val recipientEmail = "ithod@ucc.edu.jm"
        val subject = "Subject of the email"
        val message = "Body of the email"

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        startActivity(Intent.createChooser(intent, "Send email"))
    }
}