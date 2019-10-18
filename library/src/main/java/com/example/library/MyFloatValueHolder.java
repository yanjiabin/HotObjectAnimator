package com.example.library;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyFloatValueHolder {

    //动画的属性
    private String propertyName;
    private Class mValueType;
    private final MyFrameKeySet myFrameKeySet;
    private Method mSetter;


    public MyFloatValueHolder(String propertyName, float[] values) {
        this.propertyName = propertyName;
        this.mValueType = float.class;
        myFrameKeySet = MyFrameKeySet.ofFloat(values);

    }

    public void setter() {
        char firstLetter = Character.toUpperCase(propertyName.charAt(0));
        String theRest = propertyName.substring(1);

        String methodName = "set"+firstLetter+theRest;

        try {
            mSetter = View.class.getMethod(methodName, float.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

    public void setAnimatedValue(View view, float fraction) {
        Object value = myFrameKeySet.getValue(fraction);

        try {
            mSetter.invoke(view,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
