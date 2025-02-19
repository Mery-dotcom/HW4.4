package com.geeks.hw44

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.geeks.hw44.databinding.ActivityMainBinding
import com.geeks.hw44.view.BlankFragment
import com.geeks.hw44.view.ChatFragment
import kotlin.Lazy

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ChatFragment())
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, BlankFragment())
            .commit()


        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom + imeInsets.bottom)
            insets
        }
    }
}