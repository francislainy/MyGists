package com.francislainy.gists.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.francislainy.gists.fragments.FragmentCounter
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.bottom_bar_navigation.*
import kotlinx.android.synthetic.main.toolbar_widget.*

const val FIRST = 1
const val SECOND = 2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.francislainy.gists.R.layout.activity_main)

        bottomBarNavigationView()
    }

    private fun bottomBarNavigationView() {

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigation.selectedItemId = com.francislainy.gists.R.id.tab_home
    }

    private val onNavigationItemSelectedListener = object : OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {
                com.francislainy.gists.R.id.tab_home -> {
//                    displayView(HOME_MAIN)

                    replaceFragment()

                    return true
                }

                com.francislainy.gists.R.id.tab_fitness -> {
//                    displayView(HOME_MAIN)

                    replaceFragment()

                    return true
                }

                com.francislainy.gists.R.id.tab_nutrition -> {
//                    displayView(HOME_MAIN)

                    replaceFragment()

                    return true
                }

                com.francislainy.gists.R.id.tab_eap -> {
//                    displayView(HOME_MAIN)

                    replaceFragment()

                    return true
                }

                com.francislainy.gists.R.id.tab_more -> {
//                    displayView(HOME_MAIN)

                    replaceFragment()

                    return true
                }
            }
            return false
        }
    }

    private fun replaceFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(com.francislainy.gists.R.id.container_body, FragmentCounter()).commit()
    }

    fun toolbarSetUP(pos: Int) {

        when(pos) {

           FIRST -> tvToolBarTitle.text = "First"
           SECOND -> tvToolBarTitle.text = "Second"
        }

    }
}