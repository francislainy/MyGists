package com.francislainy.gists.fragments

import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.util.FIRST
import com.francislainy.gists.util.toast
import kotlinx.android.synthetic.main.fragment_counter.*
import java.util.*
import kotlin.collections.LinkedHashMap


class FragmentCounter : Fragment() {

    private var v: Vibrator? = null

    private var questionsList = arrayListOf(5, 1, 15, 2) // List with questions that will appear for the user to guess
    private var question: Int? = null
    private var indexQuestion = 0

    private var map: LinkedHashMap<Int, ArrayList<Int>> = LinkedHashMap()

    private lateinit var btnList: List<Button>

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).displayToolbar(FIRST)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.francislainy.gists.R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnList = listOf<Button>(btn1, btn2, btn3, btn4)
        for (b in btnList) {
            b.setOnClickListener(onClickButton)
        }

        question = questionsList[indexQuestion]
        tvCounter.text = question.toString()
        indexQuestion++

        populatePossibleAnswers(0, arrayListOf(5, 10, 15, 20))
        populatePossibleAnswers(1, arrayListOf(1, 5, 15, 20))
        populatePossibleAnswers(2, arrayListOf(5, 10, 15, 20))
        populatePossibleAnswers(3, arrayListOf(5, 10, 2, 20))

        for ((index, answer) in map[question!!]!!.withIndex()) { // question.value = answerList

            btnList[index].text = answer.toString()
        }

//        setUpVibrator()
//
//        btnGoToNext.setOnClickListener(onClickGoToNext)
    }

    private fun populatePossibleAnswers(correctAnswer: Int, possibleAnswersList: ArrayList<Int>) {
//        answersList.shuffle()
        map[questionsList!![correctAnswer]] = possibleAnswersList
    }

    private val onClickButton = View.OnClickListener {

        val btn: Button? = view?.findViewById(it.id)

        if (indexQuestion == questionsList?.size) { // Restarts the game and avoids crash on last question
            indexQuestion = 0
        }

        if (btn?.text == tvCounter.text) {

            activity?.toast("Right")

            question = questionsList?.get(indexQuestion)
            tvCounter.text = question.toString()
            indexQuestion++


            for ((index, answer) in map[question!!]!!.withIndex()) { // question.value = answerList

                btnList[index].text = answer.toString()
            }

        }

    }


//    private fun setUpVibrator() {
//        v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
//
//        object : CountDownTimer(3000, 1000) {
//
//            override fun onTick(millisUntilFinished: Long) {
//                tvCounter?.text = "seconds remaining: " + millisUntilFinished / 1000
//            }
//
//            override fun onFinish() {
//                tvCounter?.text = "done!"
//                vibrateDevice()
//                v?.cancel()
//            }
//
//        }.start()
//    }
//
//    private val onClickGoToNext = View.OnClickListener {
//
//        (activity as MainActivity).displayView(SECOND)
//    }
//
//    fun vibrateDevice() {
//
//        // Vibrate for 500 milliseconds
//        if (SDK_INT >= O) {
//            v!!.vibrate(createOneShot(500, DEFAULT_AMPLITUDE))
//        } else {
//            //deprecated in API 26
//            v!!.vibrate(500)
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//
//        v?.cancel()
//    }

}
