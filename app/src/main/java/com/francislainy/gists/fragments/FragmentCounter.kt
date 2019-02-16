package com.francislainy.gists.fragments

import android.content.Context
import android.os.Build
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
import androidx.fragment.app.Fragment
import com.francislainy.gists.activities.MainActivity
import com.francislainy.gists.util.FIRST
import com.francislainy.gists.util.SECOND
import kotlinx.android.synthetic.main.fragment_counter.*


class FragmentCounter : Fragment() {

    private var v: Vibrator? = null

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).displayToolbar(FIRST)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.francislainy.gists.R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpVibrator()

        btnGoToNext.setOnClickListener(onClickGoToNext)
    }

    private fun setUpVibrator() {
        v = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tvCounter?.text = "seconds remaining: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                tvCounter?.text = "done!"
                vibrateDevice()
                v?.cancel()
            }

        }.start()
    }

    private val onClickGoToNext = View.OnClickListener {

        (activity as MainActivity).displayView(SECOND)
    }

    fun vibrateDevice() {

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
