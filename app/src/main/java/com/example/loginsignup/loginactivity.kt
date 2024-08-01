package com.example.loginsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignup.databinding.ActivityLoginactivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class loginactivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginactivityBinding
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        val currentuser:FirebaseUser?=auth.currentUser
        if(currentuser!=null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loginactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityLoginactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()


        binding.signup.setOnClickListener{
            val intent=Intent(this,signupactivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.login.setOnClickListener{

            val email=binding.email.text.toString()
            val password=binding.username.text.toString()
            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "enter detail", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){task->
                        if(task.isSuccessful){
                            Toast.makeText(this, "sign in successfull", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()

                        }else{
                            Toast.makeText(this, "failed:${task.exception?.message}", Toast.LENGTH_SHORT).show()

                        }

                    }
            }
        }

        binding.forgetbutton.setOnClickListener{
            startActivity(Intent(this,resertpassword::class.java))
            finish()
        }

    }
}