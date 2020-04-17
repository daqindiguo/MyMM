package com.scott.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.PopupWindow
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


open class BasePopupWindow<T>(var context: Context, var parent: View, @LayoutRes layoutId: Int, var cancel: Boolean)
    : PopupWindow(LayoutInflater.from(context).inflate(layoutId, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true) {

    constructor(context: Context, parent: View, @LayoutRes layoutId: Int) : this(context, parent, layoutId, true)

    var isDismiss: Boolean = false
    var enterAnimation: Animation? = null
    var exitAnimation: Animation? = null

    init {
//        animationStyle = R.style.popupWindow_anim_bottom

        setCancel(cancel)

        if (!cancel)
            setTouchInterceptor { v, event ->
                if (isTouchPointInView(v, event.rawX, event.rawY)) {
                    isDismiss = true
                }

                false
            }
    }

    private fun isTouchPointInView(view: View?, x: Float, y: Float): Boolean {
        if (view == null) {
            return false
        }
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val left = location[0]
        val top = location[1]
        val right = left + view.measuredWidth
        val bottom = top + view.measuredHeight

        return (y >= top && y <= bottom && x >= left
                && x <= right)
    }

    /**
     * 外部是否可以点击 点击外部取消
     */
    private fun setCancel(cancel: Boolean): BasePopupWindow<T> {
        isOutsideTouchable = cancel
        isFocusable = cancel
        setBackgroundDrawable(ColorDrawable())
        return this
    }

    /**
     * 显示的位置
     */
    fun show(): BasePopupWindow<T> {
        return show(Gravity.BOTTOM, 0, 0)
    }

    fun show(gravity: Int, x: Int, y: Int):BasePopupWindow<T> {
        setObscure()
        return showAtLocation(gravity, x, y)
    }

    fun showAtLocation(gravity: Int, x: Int, y: Int):BasePopupWindow<T> {
        if (enterAnimation == null) {
            enterAnimation = getEnterAnimation(300)
        }
        contentView.animation = enterAnimation
        showAtLocation(parent, gravity, x, y)
        return this
    }

    fun showAsDropDown(x: Int, y: Int): BasePopupWindow<T> {
        if (enterAnimation == null) {
            enterAnimation = getEnterAnimation(300)
        }
        contentView.animation = enterAnimation
        showAsDropDown(parent, x, y)
        return this
    }

    override fun dismiss() {
        if (cancel || isDismiss) {
            if (exitAnimation == null) {
                exitAnimation = getExitAnimation(200)
            }
            contentView.startAnimation(exitAnimation)
            contentView.postDelayed({ super.dismiss() }, exitAnimation?.duration ?: 200)
        }
    }

    fun getEnterAnimation(duration: Long): Animation {
        val enterAnimation: Animation = TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1f, TranslateAnimation.RELATIVE_TO_SELF, 0f)

        enterAnimation.duration = duration
        enterAnimation.interpolator = DecelerateInterpolator()
        return enterAnimation
    }

    fun getExitAnimation(duration: Long): Animation {
        val exitAnimation: Animation = TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 1f)
        exitAnimation.duration = duration
        exitAnimation.interpolator = AccelerateInterpolator()
        return exitAnimation
    }

    /**
     * 背景变暗
     */
    private fun setObscure(): BasePopupWindow<T> {
        val activity = context as AppCompatActivity
//        SystemUtil.darkenBackground(activity, 0.8f)

        val rootView = activity.window.decorView.findViewById<FrameLayout>(android.R.id.content)
        val dimView = View(context)

        val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        dimView.layoutParams = lp
        dimView.setBackgroundColor(Color.parseColor("#30000000"))

        rootView.addView(dimView)

        setOnDismissListener {
            rootView.removeView(dimView)
//            SystemUtil.darkenBackground(activity, 1f)
        }
        return this
    }

}
