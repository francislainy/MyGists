package com.francislainy.gists.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.activities.SECOND
import kotlinx.android.synthetic.main.fragment_counter.*


class FragmentCounterInner : Fragment() {

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).toolbarSetUP(SECOND)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.francislainy.gists.R.layout.fragment_counter_inner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCounter.text = "second"
    }
}
