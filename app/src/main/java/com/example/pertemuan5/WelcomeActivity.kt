package com.example.pertemuan5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan5.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity()  {

    private lateinit var binding : ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnWelcome.setOnClickListener {
                val intentToSecondActivity =
                    Intent (this@WelcomeActivity, StartedActivity::class.java)

                startActivity(intentToSecondActivity)
            }
        }
    }
}