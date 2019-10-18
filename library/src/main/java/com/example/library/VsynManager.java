package com.example.library;

import java.util.ArrayList;
import java.util.List;

public class VsynManager {


    private List<AnimatorFrameCallBack> frameCallBackList;
    private static VsynManager instance = new VsynManager();

    private VsynManager(){
        frameCallBackList = new ArrayList<>();


        new Thread(runnable).start();
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(16);
                    for (AnimatorFrameCallBack animatorFrameCallBack : frameCallBackList) {
                        animatorFrameCallBack.animatorFrameCallback(System.currentTimeMillis());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    public static VsynManager getInstance(){
        return instance;
    }


    interface AnimatorFrameCallBack{
        boolean animatorFrameCallback(long time);
    }

    public void addListener(AnimatorFrameCallBack listener){
        frameCallBackList.add(listener);
    }

}
