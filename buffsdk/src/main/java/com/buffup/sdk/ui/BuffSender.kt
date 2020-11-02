package com.buffup.sdk.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.buffup.sdk.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.buff_sender.view.*

internal class BuffSender: FrameLayout {

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
        View.inflate(context, R.layout.buff_sender_v2, this)
    }

    fun fillData(name: String, image: String? = null) {
        sender_name.text = name
        Glide.with(context)
            .load(image)
            .apply(RequestOptions.circleCropTransform())
            .into(sender_image)
    }

}