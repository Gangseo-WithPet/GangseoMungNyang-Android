package com.jiwondev.withpet.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jiwondev.withpet.R
import com.jiwondev.withpet.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(host.navController)
    }
}