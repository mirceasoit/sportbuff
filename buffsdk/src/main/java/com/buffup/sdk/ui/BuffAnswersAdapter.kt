package com.buffup.sdk.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buffup.sdk.R
import com.buffup.sdk.model.Answer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.buff_answer.view.*

internal class BuffAnswersAdapter(val clickCallback: () -> Unit) : RecyclerView.Adapter<BuffAnswersAdapter.AnswerViewHolder>() {

    var buffAnswersList = emptyList<Answer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder =
        AnswerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.buff_answer, parent, false))

    override fun getItemCount(): Int = buffAnswersList.size

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(buffAnswersList[position])
    }

    inner class AnswerViewHolder(private val buffAnswerView: View) : RecyclerView.ViewHolder(buffAnswerView) {

        init {
            buffAnswerView.setOnClickListener { clickCallback.invoke() }
        }

        fun bind(answer: Answer) {
            buffAnswerView.answer_text.text = answer.title
            Glide.with(buffAnswerView.context).load(answer.getImageUrl(buffAnswerView.context)).centerInside().into(buffAnswerView.answer_image)
        }
    }
}