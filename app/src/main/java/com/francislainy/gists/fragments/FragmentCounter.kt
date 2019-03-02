package com.francislainy.gists.fragments

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.VibrationEffect.createOneShot
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


class FragmentCounter : Fragment() {

    private var v: Vibrator? = null

    private lateinit var questionsList: ArrayList<Int>
    private var question: Int? = null
    private var indexQuestion = 0

    private var map: LinkedHashMap<Int, ArrayList<String>> = LinkedHashMap()

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

        v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        btnList = listOf<Button>(btn1, btn2, btn3, btn4)
        for (b in btnList) {
            b.setOnClickListener(onClickButton)
        }

        questionsList = arrayListOf(5, 1, 15, 2) // List with questions that will appear for the user to guess

        question = questionsList[indexQuestion]
        tvCounter.text = question.toString()
        indexQuestion++

        loadAllPossibleAnswers()

        for ((index, answer) in map[question!!]!!.withIndex()) {

            btnList[index].text = answer
        }
    }

    private fun loadAllPossibleAnswers() {
        populatePossibleAnswers(0, arrayListOf("1+4", "9+1", "13+2", "10+10"))
        populatePossibleAnswers(1, arrayListOf("1+0", "5+2", "15 + 1", "3+3"))
        populatePossibleAnswers(2, arrayListOf("5+12", "10+3", "7+8", "6+4"))
        populatePossibleAnswers(3, arrayListOf("5+7", "1+1", "2+2", "4+3"))
    }

    private fun populatePossibleAnswers(intKey: Int, possibleAnswersList: ArrayList<String>) {
        possibleAnswersList.shuffle()
        map[questionsList[intKey]] = possibleAnswersList
    }

    private val onClickButton = View.OnClickListener {

        val btn: Button? = view?.findViewById(it.id)

        if (indexQuestion == questionsList.size) { // Restarts the game and avoids crash on last question
            indexQuestion = 0

            loadAllPossibleAnswers()
        }

        val text = btn?.text.toString()
        val num = text.split("+")
        val numA = num[0].toInt()
        val numB = num[1].toInt()

        if ((numA + numB).toString() == tvCounter.text) {

            activity?.toast("Right")
            vibrateDevice()

            question = questionsList[indexQuestion]
            tvCounter.text = question.toString()
            indexQuestion++


            for ((index, answer) in map[question!!]!!.withIndex()) { // question.value = answerList

                btnList[index].text = answer
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

//    private val onClickGoToNext = View.OnClickListener {
//
//        (activity as MainActivity).displayView(SECOND)
//    }

    private fun vibrateDevice() {

        // Vibrate for 500 milliseconds
        if (SDK_INT >= O) {
            v!!.vibrate(createOneShot(500, DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v!!.vibrate(500)
        }
    }

//    override fun onStop() {
//        super.onStop()
//
//        v?.cancel()
//    }

}
