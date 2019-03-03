package com.francislainy.gists.fragments

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.VibrationEffect.createOneShot
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import androidx.fragment.app.Fragment
import com.francislainy.gists.R
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.util.FIRST
import com.francislainy.gists.util.setColor
import com.francislainy.gists.util.toast
import com.github.lzyzsd.circleprogress.DonutProgress
import kotlinx.android.synthetic.main.fragment_counter.*


private val LOG_TAG: String? = "FragmentCounter"

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

        v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.francislainy.gists.R.layout.fragment_counter, container, false)
    }

    private lateinit var donutTimer: DonutProgress
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        donutTimer = view.findViewById(R.id.donutTimer)

        questionsList = arrayListOf(5, 1, 15, 2) // List with questions that will appear for the user to guess

        v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        btnList = listOf<Button>(btn1, btn2, btn3, btn4)
        for (b in btnList) {
            b.setOnClickListener(onClickButton)
        }


        question = questionsList[indexQuestion]
        tvQuestion.text = question.toString()
        indexQuestion++

        loadAllPossibleAnswers()

        populateButtonsWithAnswers()


        object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                donutTimer.text = (millisUntilFinished / 1000).toString()
                donutTimer.progress = (millisUntilFinished / 1000).toFloat()
            }

            override fun onFinish() {
                vibrateDevice()
                v?.cancel()

                donutTimer.apply {
                    text = "done!"
                    textColor = setColor(activity as MainActivity, R.color.white)
                    innerBackgroundColor = setColor(activity as MainActivity, R.color.colorPrimary)
                    unfinishedStrokeColor = setColor(activity as MainActivity, R.color.colorPrimary)
                    finishedStrokeColor = setColor(activity as MainActivity, R.color.colorPrimary)
                }
            }

        }.start()


        updateDonutProgress()
    }

    private fun updateDonutProgress() {

        activity!!.runOnUiThread {
            val anim = ObjectAnimator.ofFloat(
                donutTimer,
                "progress",
                0f,
                30f
            ) // Start on 30 seconds (set on xml - donut_max property)
            anim.interpolator = DecelerateInterpolator()
            anim.duration = 500
            anim.start()
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

        if ((numA + numB).toString() == tvQuestion.text) {

            activity?.toast("Right")
            vibrateDevice()

            question = questionsList[indexQuestion]
            tvQuestion.text = question.toString()
            indexQuestion++


            populateButtonsWithAnswers()

        } else {
            vibrateDevice()
        }

    }

    private fun populateButtonsWithAnswers() {
        for ((index, answer) in map[question!!]!!.withIndex()) {

            btnList[index].text = answer
        }
    }

    private fun vibrateDevice() {

        // Vibrate for 500 milliseconds
        if (SDK_INT >= O) {
            v!!.vibrate(createOneShot(500, DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v!!.vibrate(500)
        }
    }

    override fun onStop() {
        super.onStop()

        v?.cancel()
    }

}
