package com.yunlonglee.thread;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 21/10/21 7:53 下午
 */
public class MyH2o {
    private static final String H = "H";
    private static final String O = "O";

    private static Queue<String> hydrogenList = new LinkedList<>();
    private static Queue<String> oxygenList = new LinkedList<>();


    public static void main(String[] args) {
        String origin = "";
        String[] originChars = origin.split("");
        List<String> origins = Arrays.asList(originChars);
        for(int i=0;i<origins.size();i++){
            if(H.equals(origins.get(i))){
                hydrogenList.size();
            }
        }
    }



    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        releaseHydrogen = new Runnable(){
            @Override
            public void run() {
                while(!hydrogenList.isEmpty()){
                    hydrogenList.poll();
                }
            }
        };
        releaseHydrogen.run();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        releaseOxygen = new Runnable(){
            @Override
            public void run() {
                while(!oxygenList.isEmpty()){
                    oxygenList.poll();
                }
            }
        };
        releaseOxygen.run();
    }
}
