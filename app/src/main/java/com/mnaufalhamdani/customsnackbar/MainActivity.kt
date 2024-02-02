package com.mnaufalhamdani.customsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mnaufalhamdani.customsnackbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSuccess.setOnClickListener {
            CustomSnackbar(binding.root, TypeMessage.SUCCESS, "Test Snackbar").show()
        }
    }
}