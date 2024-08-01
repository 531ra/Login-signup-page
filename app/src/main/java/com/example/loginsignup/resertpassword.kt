package com.example.loginsignup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignup.databinding.ActivityResertpasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class resertpassword : AppCompatActivity() {
    private lateinit var binding: ActivityResertpasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resertpassword)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding=ActivityResertpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        binding.reset.setOnClickListener{

            val email=binding.email.text.toString()
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "invalid pattern", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            if(email.isEmpty() )
                Toast.makeText(this, "Please fill email", Toast.LENGTH_SHORT).show()

            else{
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this){task->
                        if(task.isSuccessful){
                            val key="hey"
                            Toast.makeText(this, "verification send to email", Toast.LENGTH_SHORT).show()
                            val intent= Intent(this,entercode::class.java)
                            intent.putExtra(key,email)
                            startActivity(intent)
                            finish()

                        }else{



                             Toast.makeText(this, "faild:${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }


                    }
            }

        }




    }



    }
