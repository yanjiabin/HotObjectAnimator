package com.example.library;

import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class MyObjectAnimator implements VsynManager.AnimatorFrameCallBack {
    //动画时长
    private long mDuration = 0;
    private WeakReference<View> target;
    private MyFrameKeySet myFrameKeySet;
    private final MyFloatValueHolder holder;
    private int index = 0;
    private TimeInterpolator interpolator;

    public void setDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    public MyObjectAnimator(View view, String propertyName, float[] values) {
        target = new WeakReference<>(view);
        holder = new MyFloatValueHolder(propertyName,values);
    }

    public static MyObjectAnimator ofFloat(View view, String propertyName, float... values) {

        MyObjectAnimator animator = new MyObjectAnimator(view,propertyName,values);
        return animator;
    }

    public void start() {

        holder.setter();
        VsynManager.getInstance().addListener(this);

    }

    @Override
    public boolean animatorFrameCallback(long time) {
        //后续的效果渲染
        //动画的总帧数
        float total = mDuration / 16;
        //拿到执行百分比 （index）/total
        float fraction = (index++) / total;
        //通过插值器去改变对应的执行百分比
        if (interpolator != null) {
            fraction = interpolator.getInterpolator(fraction);
        }
        //循环 repeat
        if (index >= total) {
            index = 0;
        }
        //交给mFloatPropertyValuesHolder，改变对应的属性值
        holder.setAnimatedValue(target.get(), fraction);
        return false;
    }
}
