package com.example.app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateBinding()
        setContentView(binding.root)
        getDataFromIntentAndPrint()
    }

    private fun inflateBinding(){
        binding = ActivitySecondBinding.inflate(layoutInflater)
    }

    private fun getDataFromIntentAndPrint(){
        val intent = intent.extras

        val username = intent?.getString("username")
        val image = intent?.getString("image")
        val url = intent?.getString("url")

            binding.run {
                secondActivityUsername.text = username
                secondActivityImage.text = image
                secondActivityApiName.text = url
            }
    }
}