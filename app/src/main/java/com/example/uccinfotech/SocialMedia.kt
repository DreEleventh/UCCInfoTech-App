package com.example.uccinfotech

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SocialMedia : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media)

        val faceBookBTH = findViewById<ImageView>(R.id.facebookBtn)
        faceBookBTH.setOnClickListener {
//            val url = "https://www.facebook.com/uccjamaica/"
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse(url)
//            startActivity(intent)

            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.android.com"))

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
}