package com.scott.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * @describe: 监听软件盘
 * @other:
 */
public class SoftKeyBoardListener {
    private View rootView;//activity的根视图
    private int rootViewVisibleHeight;//纪录根视图的显示高度
    private int rootViewVisibleHeight1;//纪录根视图的显示高度
    private ViewGroup.LayoutParams frameLayoutParams;
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

    public static SoftKeyBoardListener getInstance(Activity activity){
        return new SoftKeyBoardListener(activity.getWindow().getDecorView().findViewById(android.R.id.content));
    }

    public static SoftKeyBoardListener getInstance(View view){
        return new SoftKeyBoardListener(view);
    }

    private SoftKeyBoardListener(View view) {
        //获取activity的根视图
        rootView = view;

        frameLayoutParams = rootView.getLayoutParams();
        //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            //获取当前根视图在屏幕上显示的大小
            Rect r = new Rect();
            rootView.getWindowVisibleDisplayFrame(r);

            int visibleHeight = r.height();
            if (rootViewVisibleHeight == 0) {
                rootViewVisibleHeight = visibleHeight;
                return;
            }

            //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
            if (rootViewVisibleHeight == visibleHeight) {
                return;
            }

            int usableHeightNow = r.bottom;
            if (usableHeightNow != rootViewVisibleHeight1) {
                //如果两次高度不一致
                //将计算的可视高度设置成视图的高度
                frameLayoutParams.height = usableHeightNow;
                rootView.requestLayout();//请求重新布局
                rootViewVisibleHeight1 = usableHeightNow;
            }

            //根视图显示高度变小超过200，可以看作软键盘显示了
            if (rootViewVisibleHeight - visibleHeight > 200) {
                if (onSoftKeyBoardChangeListener != null) {
                    onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                }
                rootViewVisibleHeight = visibleHeight;
                return;
            }

            //根视图显示高度变大超过200，可以看作软键盘隐藏了
            if (visibleHeight - rootViewVisibleHeight > 200) {
                if (onSoftKeyBoardChangeListener != null) {
                    onSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                }
                rootViewVisibleHeight = visibleHeight;
                return;
            }
        });
    }

    public void setListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardShow(int height);

        void keyBoardHide(int height);
    }
}