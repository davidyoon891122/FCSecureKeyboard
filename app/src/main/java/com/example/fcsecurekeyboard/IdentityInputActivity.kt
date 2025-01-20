package com.example.fcsecurekeyboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fcsecurekeyboard.databinding.ActivityIdentityInputBinding

class IdentityInputActivity: AppCompatActivity() {
    private lateinit var binding: ActivityIdentityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.view = this
    }
}