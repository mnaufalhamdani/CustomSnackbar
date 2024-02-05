package com.mnaufalhamdani.customsnackbar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mnaufalhamdani.customsnackbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSuccess.setOnClickListener {
            CustomSnackbar(binding.root, TypeMessage.SUCCESS, "Ini alert Success. (Baris 1)\n(Baris 2)\n(Baris 3)").show()
        }

        binding.btnInfo.setOnClickListener {
            CustomSnackbar(binding.root, TypeMessage.INFO, "Ini alert Info").show()
        }

        binding.btnWarning.setOnClickListener {
            CustomSnackbar(binding.root, TypeMessage.WARNING, "Ini alert Warning").show("Update", action = {
                Toast.makeText(binding.root.context, "Ini dari tombol", Toast.LENGTH_SHORT).show()
            })
        }

        binding.btnError.setOnClickListener {
            CustomSnackbar(binding.root, TypeMessage.ERROR, "Ini alert Warning").show()
        }
    }
}