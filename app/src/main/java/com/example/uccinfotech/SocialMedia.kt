package com.example.uccinfotech

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class SocialMedia : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media)

        val drawerLayout = findViewById<DrawerLayout>(R.id.socialMediaDrawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close )
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
            true
        }


        val faceBookBTH = findViewById<ImageView>(R.id.facebookBtn)
        faceBookBTH.setOnClickListener {
            val url = "https://www.facebook.com/uccjamaica/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)

        }

        val instaBTN = findViewById<ImageView>(R.id.instagramBtn)
        instaBTN.setOnClickListener{
            val url = "https://www.instagram.com/uccjamaica/?hl=en"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        val twitterBTN = findViewById<ImageView>(R.id.twitterBtn)
        twitterBTN.setOnClickListener {
            val url = "https://twitter.com/UCCjamaica"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}