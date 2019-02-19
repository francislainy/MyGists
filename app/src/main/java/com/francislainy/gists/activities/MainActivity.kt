package com.francislainy.gists.activities

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.francislainy.gists.R
import com.francislainy.gists.fragments.FragmentDrawer
import com.francislainy.gists.fragments.FragmentDrawer.FragmentDrawerListener
import com.francislainy.gists.util.FIRST
import com.francislainy.gists.util.ToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_bar_navigation.*

class MainActivity : AppCompatActivity(), FragmentDrawerListener {

    private var exit: Boolean? = false
    private var drawerFragment: FragmentDrawer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.francislainy.gists.R.layout.activity_main)

        bottomBarNavigationView()

        setUpDrawer()

        toolbarActionBarSetUP()
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        displayView(position)
    }

    override fun onBackPressed() {

        val count = supportFragmentManager.backStackEntryCount

        when (count) {
            0 -> pressAgainToExit()
            else -> supportFragmentManager.popBackStack()
        }
    }

    private fun pressAgainToExit() {

        if (exit!!) {
            finish() // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show()

            exit = true
            Handler().postDelayed({ exit = false }, (3 * 1000).toLong())
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        drawerFragment?.openDrawer()
        return true
    }

    private fun toolbarActionBarSetUP() {
        // Toolbar (ActionBar) setup
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp) // set Hamburger default back to the Actionbar ;)
    }

    private fun setUpDrawer() {
        drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as FragmentDrawer
        drawerFragment!!.setUp(
            R.id.fragment_navigation_drawer,
            findViewById(R.id.drawer_layout),
            toolbar as Toolbar
        )
        drawerFragment!!.setDrawerListener(this)
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