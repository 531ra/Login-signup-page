package com.example.loginsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignup.databinding.ActivityEntercodeBinding
import com.google.firebase.auth.FirebaseAuth

class entercode : AppCompatActivity() {
    private lateinit var binding: ActivityEntercodeBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_entercode)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityEntercodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
        binding.resetBtn.setOnClickListener{
            val code=binding.code.text.toString()
            val password=binding.password.text.toString()
            val repeatpassword=binding.repeatPassword.text.toString()
          val email=intent.getStringExtra("hey")

            if (password.isNotEmpty() && password == repeatpassword) {
                if (email != null) {
                    resetPassword(email, code, password)
                }
            } else {
                Toast.makeText(this, "Passwords do not match or are empty.", Toast.LENGTH_SHORT).show()
            }
        }



        }

    private fun resetPassword(email: String, code: String, newPassword: String) {
        auth.verifyPasswordResetCode(code)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    auth.confirmPasswordReset(code, newPassword)
                        .addOnCompleteListener { resetTask ->
                            if (resetTask.isSuccessful) {
                                Toast.makeText(this, "Password has been reset.", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this,loginactivity::class.java))
                                finish()
                                // Navigate to login or main activity
                            } else {
                                Toast.makeText(this, "Failed to reset password ${resetTask.exception?.message}.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Invalid verification code.", Toast.LENGTH_SHORT).show()
                }
            }
}
}