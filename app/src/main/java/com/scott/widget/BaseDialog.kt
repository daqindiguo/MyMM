package com.scott.widget

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.scott.mymm.R
import com.scott.utils.AntiShakeOnClickListener
import com.scott.utils.SoftKeyBoardListener


open class BaseDialog<T>(
    context: Context, @LayoutRes layoutId: Int,
    private val cancelable: Boolean,
    private val dim: Float
) : Dialog(context, R.style.CustomDialog) {

    constructor(context: Context, layoutId: Int) : this(context, layoutId, true, 0.3f)

    constructor(context: Context, layoutId: Int, cancelable: Boolean) : this(
        context,
        layoutId,
        cancelable,
        0.3f
    )

    var inputEnabled: Boolean = false
    var view: View = LayoutInflater.from(context).inflate(layoutId, null)
    var anim: Int? = R.style.DialogAnim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)

        setCancelable(cancelable)
        setCanceledOnTouchOutside(cancelable)
        if (cancelable)
            view.setOnClickListener(object : AntiShakeOnClickListener() {
                override fun antiShakeOnClick(v: View?) {
                    dismiss()
                }
            })

        window?.run {
            anim?.let {
                setWindowAnimations(it)
            }
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            decorView.systemUiVisibility = decorView.systemUiVisibility
            if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = Color.TRANSPARENT
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }

            setDimAmount(dim)
            if (inputEnabled)
                SoftKeyBoardListener.getInstance(decorView.findViewById<View>(android.R.id.content))
        }
    }

    fun setAnimationStyle(@StyleRes anim: Int?): BaseDialog<T> {
        this.anim = anim
        return this
    }

}
