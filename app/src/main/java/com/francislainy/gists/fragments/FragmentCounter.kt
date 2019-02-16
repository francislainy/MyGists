package com.francislainy.gists.fragments

import android.content.Context.VIBRATOR_SERVICE
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.francislainy.gists.activities.FIRST
import com.francislainy.gists.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_counter.*


class FragmentCounter : Fragment() {

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).toolbarSetUP(FIRST)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.francislainy.gists.R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoToNext.setOnClickListener ( onClickGoToNext )

        object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tvCounter?.text = "seconds remaining: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                tvCounter?.text = "done!"
                vibrateDevice()
            }

        }.start()
    }

    private val onClickGoToNext = View.OnClickListener {

        Toast.makeText(activity, "clicked", Toast.LENGTH_SHORT).show()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        fragmentManager.beginTransaction().replace(com.francislainy.gists.R.id.container_body, FragmentCounterInner())
            .commit()

    }

    fun vibrateDevice() {

        val v = activity!!.getSystemService(VIBRATOR_SERVICE) as Vibrator?
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v!!.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v!!.vibrate(500)
        }
    }
}
