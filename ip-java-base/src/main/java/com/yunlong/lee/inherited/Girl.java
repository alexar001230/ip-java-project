package com.yunlong.lee.inherited;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 2/2/22 4:40 上午
 */
public class Girl extends Person {

    public static String NAME = "father";

    @Override
    public String eat(String foodName) {
        return "Girl eat" + foodName;
    }

    @Override
    public String speak(String msg) {
        return "Girl" + msg;
    }
}
