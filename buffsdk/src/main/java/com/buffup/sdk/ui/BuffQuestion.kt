package com.buffup.sdk.ui

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.buffup.sdk.R
import com.buffup.sdk.model.Question
import kotlinx.android.synthetic.main.buff_question.view.*
import kotlin.math.floor

class BuffQuestion : FrameLayout {

    var current = 0
    var listener: Listener? = null

    private val countDownTimer by lazy {
        object : CountDownTimer((question_time_progress.max.toLong() + 1), 250) {

            override fun onFinish() {
                listener?.onTimerEnded()
            }

            override fun onTick(currentTimeMs: Long) {
                val currentSeconds = floor(currentTimeMs / 1000.0).toInt()
                current = currentSeconds
                question_time.text = "$current"
                question_time_progress.progress = question_time_progress.max - currentTimeMs.toInt()
            }
        }
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.buff_question_v2, this)
    }

    private fun startCountDownTimer() {
        countDownTimer.cancel()
        countDownTimer.start()
    }

    fun stopCountDownTimer() {
        countDownTimer.cancel()
    }

    private fun setCountDownTimer(max: Int) {
        question_time_progress.max = max * 1000
        question_time_progress.progress = 0
        question_time.text = "$max"
    }

    fun fillData(question: Question, timeToShow: Int) {
        question_text.text = question.title
        setCountDownTimer(timeToShow)
        startCountDownTimer()
    }

    interface Listener {
        fun onTimerEnded()
    }

}