package com.scott.utils

import android.view.View

abstract class AntiShakeOnClickListener : View.OnClickListener {

    override fun onClick(v: View) {
        if (AntiShakeUtils.isInvalidClick(v)) return
        antiShakeOnClick(v)
    }

    abstract fun antiShakeOnClick(v: View?)

}