package com.buffup.sdk.model

data class Buff(val id: Long, val timeToShow: Int, val priority: Int, val author: Author, val question: Question, val answers: List<Answer>, val language: String)