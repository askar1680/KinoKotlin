package kz.kotlin.x10app.commons.extensions

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

fun View.shakeView(duration: Int = 1, offset: Int = 5) {
    val anim = TranslateAnimation((-offset).toFloat(), offset.toFloat(), 0f, 0f)
    anim.duration = duration.toLong()
    anim.repeatMode = Animation.REVERSE
    anim.repeatCount = 5
    startAnimation(anim)
}