package com.aditya.nlocr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.aditya.nlocr.databinding.ActivityMainBinding
import com.aditya.nlocr.entityextraction.EntityExtractionFragment
import com.aditya.nlocr.identification.LanguageIdentificationFragment
import com.aditya.nlocr.smartreply.SmartReplyFragment
import com.aditya.nlocr.translation.TranslationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(LanguageIdentificationFragment())
        binding.bottomNavigationView.menu.getItem(0).isChecked = true
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menuIdentification -> replaceFragment(LanguageIdentificationFragment())
                R.id.menuTranslation -> replaceFragment(TranslationFragment())
                R.id.menuSmartReply -> replaceFragment(SmartReplyFragment())
                R.id.menuExtraction -> replaceFragment(EntityExtractionFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit()
    }
}