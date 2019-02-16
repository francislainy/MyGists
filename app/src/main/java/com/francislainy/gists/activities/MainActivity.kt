package com.francislainy.gists.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.francislainy.gists.R
import com.francislainy.gists.util.FIRST
import com.francislainy.gists.util.ToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.bottom_bar_navigation.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.francislainy.gists.R.layout.activity_main)

        bottomBarNavigationView()
    }

    private fun bottomBarNavigationView() {

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.tab_home
    }

    private val onNavigationItemSelectedListener = object : OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                R.id.tab_home -> {
                    displayView(FIRST)

                    return true
                }

                R.id.tab_fitness -> {
//                    displayView(HOME_MAIN)

                    return true
                }

                R.id.tab_nutrition -> {
//                    displayView(HOME_MAIN)

                    return true
                }

                R.id.tab_eap -> {
//                    displayView(HOME_MAIN)

                    return true
                }

                R.id.tab_more -> {
//                    displayView(HOME_MAIN)

                    return true
                }
            }
            return false
        }
    }

    fun displayView(pos: Int) {
        ToolbarLayout(this@MainActivity).replaceFragment(pos)
    }

    fun displayToolbar(pos: Int) {
        ToolbarLayout(this@MainActivity).toolbarSetUP(pos)
    }

}