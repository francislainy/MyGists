package com.francislainy.gists.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.francislainy.gists.R
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.fragments.FragmentCounter
import com.francislainy.gists.fragments.FragmentCounterInner
import kotlinx.android.synthetic.main.toolbar_widget.*

const val FIRST = 1
const val SECOND = 2

class ToolbarLayout(private val mainActivity: MainActivity) {


    fun toolbarSetUP(pos: Int) {

        with(mainActivity) {

            mainActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            when (pos) {
                 FIRST -> {
                    tvToolBarTitle.text = "First"
                     toolbarWithHamburger()
                }
                SECOND -> {
                    tvToolBarTitle.text = "Second"
                    toolbarWithBackArrow()
                }
            }
        }
    }

    private fun toolbarWithHamburger() {
        mainActivity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
    }

    private fun toolbarWithBackArrow() {
        mainActivity.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
    }

    fun replaceFragment(pos: Int) {
        val fragmentManager: FragmentManager = mainActivity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val f: Fragment = when (pos) {
            FIRST -> FragmentCounter()
            SECOND -> FragmentCounterInner()
            else -> FragmentCounter()
        }

        if (shouldBeAddedToStackNavigation(pos.toString())) {

            // for History back Navigation ;)
            fragmentTransaction.addToBackStack(pos.toString())

            // animation :)
            fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )

        } else {

            // if is one of the 5 Main Tabs (Home, Fitness, Goals, Rewards, Nutrition)

            // don't do the animation then ;)
        }

        fragmentTransaction.replace(R.id.container_body, f, pos.toString())

        fragmentTransaction.commit()
    }

    // The 5 main Tabs (Home, Fitness, Goals, Rewards, Nutrition)
    private fun shouldBeAddedToStackNavigation(TAG: String): Boolean = when (TAG) {

        "FIRST", "HOME_FITNESS", "HOME_GOALS", "HOME_REWARDS", "HOME_NUTRITION" -> false

        else -> true
    }
}