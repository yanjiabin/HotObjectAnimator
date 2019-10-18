package com.example.library;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFrameKeySet {

    private TypeEvaluator mEvaluator;
    private List<MyFloatFrameValue> mKeyFrams;

    public MyFrameKeySet(MyFloatFrameValue... frameValues) {
        this.mEvaluator = new FloatEvaluator();
        this.mKeyFrams = Arrays.asList(frameValues);
    }

    //初始化关键帧数据
    public static MyFrameKeySet ofFloat(float... values) {
        if (values.length<0){
            return null;
        }

        MyFloatFrameValue[] frameValues = new MyFloatFrameValue[values.length];
        frameValues[0] = new MyFloatFrameValue(0,values[0]);
        for (int i = 1; i < values.length; i++) {
            frameValues[i] = new MyFloatFrameValue(i/(values.length-1),values[i]);
        }
        
        return new MyFrameKeySet(frameValues);

    }

    //获取当前百分比对应得具体属性值
    public Object getValue(float fraction) {
        MyFloatFrameValue prevKeyFrame = mKeyFrams.get(0);
        for (int i = 0; i < mKeyFrams.size(); i++) {
            MyFloatFrameValue nextKeyFrame = mKeyFrams.get(i);
            if (fraction < nextKeyFrame.getPercentage()) {
                //当前百分比在此之间
                //计算间隔百分比
                float intervalFraction = (fraction - prevKeyFrame.getPercentage())
                        / (nextKeyFrame.getPercentage() - prevKeyFrame.getPercentage());
                //通过估值器返回对应得值
                return mEvaluator == null ?
                        prevKeyFrame.getmValue() + intervalFraction * (nextKeyFrame.getmValue() - prevKeyFrame.getmValue()) :
                        ((Number) mEvaluator.evaluate(intervalFraction, prevKeyFrame.getmValue(), nextKeyFrame.getmValue())).floatValue();
            }
            prevKeyFrame = nextKeyFrame;
        }
        //对应得帧不够
        return mKeyFrams.get(mKeyFrams.size() - 1).getmValue();
    }
}
