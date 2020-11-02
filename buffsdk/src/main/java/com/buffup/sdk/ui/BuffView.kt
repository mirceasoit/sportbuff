package com.buffup.sdk.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.*
import com.buffup.sdk.viewmodel.BuffViewModel
import java.util.concurrent.TimeUnit
import com.buffup.sdk.R
import com.buffup.sdk.model.*
import kotlinx.android.synthetic.main.buff_sender.view.*
import kotlinx.android.synthetic.main.buff_view.view.*

open class BuffView : FrameLayout, BuffQuestion.Listener {

    private lateinit var viewModel: BuffViewModel

    private val loadNextBuffsRunnable by lazy { Runnable { loadNextBuff() } }

    private val hideBuffRunnable by lazy { Runnable { hideBuff() } }

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
        View.inflate(context, R.layout.buff_view, this)
        viewModel = ViewModelProvider(context as ViewModelStoreOwner).get(BuffViewModel::class.java)
        viewModel.buff.observeForever(buffObserver)
        sender_view.buff_close.setOnClickListener {
            hideBuff()
        }
        question_view.listener = this
    }

    private val buffObserver = Observer<Result> { result ->
        when (result) {
            is Result.Success -> {
                fillData(result.buff)
                showBuff()
            }
            is Result.Error -> {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Error")
                builder.setMessage(result.message)
                builder.setPositiveButton(android.R.string.yes) { dialog, _ ->
                   dialog.dismiss()
                }
                builder.show()
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewModel.buff.removeObserver(buffObserver)
        buff_container.visibility = View.GONE
        question_view.stopCountDownTimer()
        removeCallbacks(loadNextBuffsRunnable)
        removeCallbacks(hideBuffRunnable)
    }

    private fun loadBuff() {
        viewModel.loadBuff((1..5L).random())
    }

    fun loadNextBuff() {
        loadBuff()
        postDelayed(loadNextBuffsRunnable, TimeUnit.SECONDS.toMillis(30))
    }

    private fun showBuff() {
        buff_container.visibility = View.VISIBLE
        buff_container.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.entry_anim
            )
        )
    }

    private fun hideBuff() {
        AnimationUtils.loadAnimation(context, R.anim.exit_anim).apply {
            this.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(anim: Animation?) {}

                override fun onAnimationEnd(anim: Animation?) {
                    buff_container.visibility = View.GONE
                }

                override fun onAnimationStart(anim: Animation?) {}

            })
        }.also { buff_container.startAnimation(it) }
    }

    private fun fillData(buff: Buff) {
        fillSender(buff.author)
        fillQuestion(buff.question, buff.timeToShow)
        fillAnswers(buff.answers)
    }

    private fun fillSender(author: Author) {
        sender_view.fillData("${author.firstName} ${author.lastName}", author.image)
    }

    private fun fillQuestion(question: Question, timeToShow: Int) {
        question_view.fillData(question, timeToShow)
    }

    private fun fillAnswers(answers: List<Answer>) {
        answers_view.adapter = BuffAnswersAdapter(::onAnswerSelected).apply { buffAnswersList = answers }
    }

    override fun onTimerEnded() {
        hideBuff()
    }

    private fun onAnswerSelected() {
        question_view.stopCountDownTimer()
        postDelayed(hideBuffRunnable, TimeUnit.SECONDS.toMillis(2))
    }
}