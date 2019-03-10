package com.francislainy.gists.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.francislainy.gists.R
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.util.FRAG_TIC_TAC
import com.francislainy.gists.util.toast
import kotlinx.android.synthetic.main.fragment_tit_tac.*

class FragmentTitTacToe : Fragment() {

    private var is0 = true //next value

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

        val b: Button = view!!.findViewById(it.id)

        if (b.text == "") {

            if (is0) {
                b.text = "0"
                checkWinner("0")
                is0 = false
            } else {
                b.text = "X"
                checkWinner("X")
                is0 = true
            }
        }
    }

    private fun checkWinner(value: String) {

        if (btn1.text == btn2.text && btn1.text == btn3.text && btn1.text != "" && btn2.text != "" && btn3.text != "") { // First row horizontal

            activity?.toast("Winner $value")
        } else if (btn1.text == btn4.text && btn1.text == btn7.text && btn1.text != "" && btn4.text != "" && btn7.text != "") { // First column vertical

            activity?.toast("Winner $value")
        } else if (btn4.text == btn5.text && btn4.text == btn6.text && btn4.text != "" && btn5.text != "" && btn6.text != "") { // Second row horizontal

            activity?.toast("Winner $value")
        } else if (btn2.text == btn5.text && btn2.text == btn8.text && btn2.text != "" && btn5.text != "" && btn8.text != "") { // Second column vertical

            activity?.toast("Winner $value")
        } else if (btn7.text == btn8.text && btn8.text == btn9.text && btn7.text != "" && btn8.text != "" && btn9.text != "") { // Third row horizontal

            activity?.toast("Winner $value")
        } else if (btn3.text == btn6.text && btn3.text == btn9.text && btn3.text != "" && btn6.text != "" && btn9.text != "") { // Third row vertical

            activity?.toast("Winner $value")
        } else if (btn1.text == btn5.text && btn1.text == btn9.text && btn1.text != "" && btn5.text != "" && btn9.text != "") { // First diagonal crossing left to right

            activity?.toast("Winner $value")
        } else if (btn3.text == btn5.text && btn3.text == btn7.text && btn3.text != "" && btn5.text != "" && btn7.text != "") { // Second diagonal crossing right to left

            activity?.toast("Winner $value")
        }

    }

}