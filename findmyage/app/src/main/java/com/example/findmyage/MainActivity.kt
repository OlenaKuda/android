package com.example.findmyage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findmyage.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateAge() }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateAge() {
        val yearOfBirth = binding.inputYearOfBirth.text.toString().toIntOrNull()
        if (yearOfBirth == null) {
            binding.outputAge.text = getString(R.string.age,"not defined")
            return
        }
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val age = currentYear - yearOfBirth
        if (age < 0 || age > 130) {
            binding.outputAge.text = getString(R.string.age, "incorrect")
            return
        }
        binding.outputAge.text = getString(R.string.age, age)
    }
}
