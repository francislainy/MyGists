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

    private var isNext0 = true // Next value
    private lateinit var buttons: List<Button>

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).displayToolbar(FRAG_TIC_TAC)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tit_tac, container, false)
    }

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

            if (isNext0) {
                b.text = "0"
                if (checkHasWinner()) {
                    activity?.toast("Winner 0")
                }
                isNext0 = false
            } else {
                b.text = "X"
                if (checkHasWinner()) {
                    activity?.toast("Winner X")
                }
                isNext0 = true
            }
        }
    }

    private fun checkIsDrawFinishGame(): Boolean {

        var isDraw = true

        for (b in buttons) {

            if (b.text == "") {
                isDraw = false
            }
        }

        return isDraw
    }

    private fun checkHasWinner(): Boolean {

        if (checkIsDrawFinishGame()) {
            activity?.toast("Draw!")

            finishGameInteractions()
        }

        return when {
            gameOver(btn1, btn2, btn3) || // First row horizontal
                    gameOver(btn1, btn4, btn7) ||  // First column vertical
                    gameOver(btn4, btn5, btn6) || // Second row horizontal
                    gameOver(btn2, btn5, btn8) || // Second column vertical
                    gameOver(btn3, btn6, btn9) || // Third row vertical
                    gameOver(btn1, btn5, btn9) || // First diagonal left to right
                    gameOver(btn3, btn5, btn7) // Second diagonal right to left
            -> true
            else -> false
        }
    }

    private fun gameOver(btnA: Button, btnB: Button, btnC: Button): Boolean {

        if (checkButtonTextEquals(btnA, btnB, btnC) && checkNotEmpty(btnA, btnB, btnC)) {

            finishGameInteractions()

            return true
        }

        return false
    }

    private fun finishGameInteractions() {
        for (b in buttons) {
            b.setOnClickListener(null) // Not allowing game to continue
        }
    }

    private fun checkNotEmpty(btnA: Button, btnB: Button, btnC: Button): Boolean {

        return btnA.text != "" && btnB.text != "" && btnC.text != ""
    }

    private fun checkButtonTextEquals(btnA: Button, btnB: Button, btnC: Button): Boolean {

        return btnA.text == btnB.text && btnA.text == btnC.text
    }
}