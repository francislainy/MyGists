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
            when (pos) {

                FIRST -> tvToolBarTitle.text = "First"
                SECOND -> tvToolBarTitle.text = "Second"
            }
        }
    }

    fun replaceFragment(pos: Int) {
        val fragmentManager: FragmentManager = mainActivity.supportFragmentManager

        val f: Fragment = when (pos) {
            FIRST -> FragmentCounter()
            SECOND -> FragmentCounterInner()
            else -> FragmentCounter()
        }

        fragmentManager.beginTransaction().replace(R.id.container_body, f).commit()
    }
}