package com.example.loginsignup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignup.databinding.ActivityWelcomescreenBinding

class welcomescreen : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomescreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcomescreen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityWelcomescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val welcometext="welcome"
        val spannableString=SpannableString(welcometext)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")),0,5,0)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#312222")),5,welcometext.length,0)

       binding.welcomeText.text=spannableString

        Handler(Looper.getMainLooper()).postDelayed({
            val intent=Intent(this,loginactivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}