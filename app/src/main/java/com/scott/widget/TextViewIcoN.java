package com.scott.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;

import com.scott.mymm.R;

public class TextViewIcoN extends androidx.appcompat.widget.AppCompatTextView {
    private Drawable drawableLeft, drawableRight, drawableTop, drawableBottom;
    private int leftWidth, leftHeight;
    private int rightWidth, rightHeight;
    private int topWidth, topHeight;
    private int bottomWidth, bottomHeight;

    public TextViewIcoN(Context context) {
        this(context, null);
    }

    public TextViewIcoN(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewIcoN(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewIcoN);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.TextViewIcoN_drawLeftWidth:
                    leftWidth = typedArray.getDimensionPixelOffset(R.styleable.TextViewIcoN_drawLeftWidth, dip2px(context, 20));
                    break;
                case R.styleable.TextViewIcoN_drawLeftHeight:
                    leftHeight = typedArray.getDimensionPixelOffset(R.styleable.TextViewIcoN_drawLeftHeight, dip2px(context, 20));
                    break;
                case R.styleable.TextViewIcoN_drawRightWidth:
                    rightWidth = typedArray.getDimensionPixelOffset(R.styleable.TextViewIcoN_drawRightWidth, dip2px(context, 20));
                    break;
                case R.styleable.TextViewIcoN_drawRightHeight:
                    rightHeight = typedArray.getDimensionPixelOffset(R.styleable.TextViewIcoN_drawRightHeight, dip2px(context, 20));
                    break;
                case R.styleable.TextViewIcoN_drawTopWidth:
                    topWidth = typedArray.getDimensionPixelOffset(R.styleable.TextViewIcoN_drawTopWidth, dip2px(context, 20));
                    break;
                case R.styleable.TextViewIcoN_drawTopHeight:
                    topHeight = typedArray.getDimensionPixelOffset(R.styleable.TextViewIcoN_drawTopHeight, dip2px(context, 20));
                    break;
                case R.styleable.TextViewIcoN_drawBottomWidth:
                    bottomWidth = typedArray.getDimensionPixelOffset(R.styleable.TextViewIcoN_drawBottomWidth, dip2px(context, 20));
                    break;
                case R.styleable.TextViewIcoN_drawBottomHeight:
                    bottomHeight = typedArray.getDimensionPixelOffset(R.styleable.TextViewIcoN_drawBottomHeight, dip2px(context, 20));
                    break;
            }
        }
        typedArray.recycle();
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(
            @Nullable Drawable left, @Nullable Drawable top,
            @Nullable Drawable right, @Nullable Drawable bottom) {
        this.drawableLeft = left;
        this.drawableTop = top;
        this.drawableRight = right;
        this.drawableBottom = bottom;
        if (left != null) {
            left.setBounds(0, 0, leftWidth, leftHeight);
        }
        if (right != null) {
            right.setBounds(0, 0, rightWidth, rightHeight);
        }
        if (top != null) {
            top.setBounds(0, 0, topWidth, topHeight);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, bottomWidth, bottomHeight);
        }
        setCompoundDrawables(left, top, right, bottom);
    }

    public static int dip2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
