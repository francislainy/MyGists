package com.francislainy.gists.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.francislainy.gists.R
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.util.FRAG_TIC_TAC
import kotlinx.android.synthetic.main.fragment_tit_tac.*

class FragmentTitTacToe : Fragment() {

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).displayToolbar(FRAG_TIC_TAC)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tit_tac, container, false)
    }

    private lateinit var buttons: List<Button>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttons = listOf<Button>(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        for (b in buttons) {
            b.setOnClickListener(onClickButton)
        }
    }

    private val onClickButton = View.OnClickListener {

        var b: Button = view!!.findViewById(it.id)

        Log.d("frandebug", b.text.toString())
        if (b.text == "") {
            b.text = "0"
        }

    }
}