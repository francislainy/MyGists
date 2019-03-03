package com.francislainy.gists.fragments

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.os.VibrationEffect.DEFAULT_AMPLITUDE
import android.os.VibrationEffect.createOneShot
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.francislainy.gists.R
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.util.FIRST
import com.francislainy.gists.util.toast
import com.github.lzyzsd.circleprogress.DonutProgress
import kotlinx.android.synthetic.main.fragment_counter.*


private val LOG_TAG: String? = "FragmentCounter"

class FragmentCounter : Fragment() {

    private var v: Vibrator? = null
    private val listObjectAnimator = java.util.ArrayList<ObjectAnimator>()

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

        for ((index, answer) in map[question!!]!!.withIndex()) {

            btnList[index].text = answer
        }

        updateDonutProgress(50f) //todo
    }

    private fun updateDonutProgress(progressParam: Float) {

        val progress: Float = if (progressParam >= 100) {
            100f // this is just in case if the user is over the target so the Donut doenst go over 100%
        } else {
            progressParam
        }

        // Donut Progress Animation :)
        activity!!.runOnUiThread {
            val anim = ObjectAnimator.ofFloat(donutTimer, "progress", 0f, progress)
            anim.interpolator = DecelerateInterpolator()
            anim.duration = 1000
            anim.start()

            listObjectAnimator.add(anim)

            val donutTimer = activity!!.findViewById(com.francislainy.gists.R.id.donutTimer) as DonutProgress

            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    Log.d(LOG_TAG, "onAnimationStart")
                }

                override fun onAnimationCancel(animation: Animator) {
                    Log.d(LOG_TAG, "onAnimationCancel")
                }

                override fun onAnimationRepeat(animation: Animator) {
                    Log.d(LOG_TAG, "onAnimationRepeat")
                }

                override fun onAnimationEnd(animation: Animator) {

                    Log.d(LOG_TAG, "onAnimationEnd")

                    if (progress >= 100) {

                        donutTimer.innerBackgroundColor = ContextCompat.getColor(activity as MainActivity, R.color.colorPrimary)

                    } else {

                        donutTimer.innerBackgroundColor = ContextCompat.getColor(activity as MainActivity, android.R.color.transparent)
                    }

                }

            })
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
