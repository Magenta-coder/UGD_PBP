package com.example.ugd3_pbp

import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val selected = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.obat -> {
                changeFragment(FragmentSearch())
                true
            }
            R.id.home -> {
                changeFragment(FragmentHome())
                true
            }
            R.id.profile -> {
                changeFragment(ProfileActivity())
                true
            }
            R.id.logout -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
                builder.setMessage("Are you sure want to exit?")
                    .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                        override fun onClick(dialogInterface: DialogInterface, i: Int) {
                            finishAndRemoveTask()
                        }
                    })
                    .setNegativeButton("No", object : DialogInterface.OnClickListener {
                        override fun onClick(dialogInterface: DialogInterface, i: Int) {
                        }
                    })

                    .show()
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        changeFragment(FragmentHome())
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(selected)

    }

    fun changeFragment(fragment: Fragment?) {
        if (fragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_fragment, fragment)
                .commit()
        }
    }
    var pref: SharedPreferences? = null

    fun getSharedPreferences(): String? {
        val username = getSharedPreferences("prefId", MODE_PRIVATE)
        return username.getString("username","Coba")
    }
}