 package com.example.loginsignup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignup.databinding.ActivitySignupactivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import java.util.regex.Pattern

 class signupactivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupactivityBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signupactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivitySignupactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()

        binding.signin.setOnClickListener{
            val intent= Intent(this,loginactivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.register.setOnClickListener{
            val email=binding.email.text.toString()
            val username=binding.username.text.toString()
            val password=binding.password.text.toString()
            val repeatpassword=binding.repeatPassword.text.toString()
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "invalid pattern", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            if(email.isEmpty() || username.isEmpty()||password.isEmpty()||repeatpassword.isEmpty())
                Toast.makeText(this, "Please fill ur Details", Toast.LENGTH_SHORT).show()
            else if(password !=repeatpassword ){
                Toast.makeText(this, "Repeat password must be same", Toast.LENGTH_SHORT).show()
            }else{
                       auth.createUserWithEmailAndPassword(email,password)
                           .addOnCompleteListener(this){task->
                               if(task.isSuccessful){
                                   Toast.makeText(this, "Register successful", Toast.LENGTH_SHORT).show()
                                   val intent= Intent(this,loginactivity::class.java)
                                   startActivity(intent)
                                   finish()

                               }else{
                                   if(task.exception is FirebaseAuthUserCollisionException){
                                       Toast.makeText(this, "user already exist", Toast.LENGTH_SHORT).show()

                                   }


                                  else Toast.makeText(this, "faild:${task.exception?.message}", Toast.LENGTH_SHORT).show()
                               }


                           }
            }

        }


    }
}