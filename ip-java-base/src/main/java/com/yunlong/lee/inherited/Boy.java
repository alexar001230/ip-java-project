package com.yunlong.lee.inherited;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 22/2/22 11:36 下午
 */
public class Boy extends Person{
    @Override
    public String eat(String foodName) {
        return "Boy eat" + foodName;
    }

    @Override
    public String speak(String msg) {
        return "Boy" + msg;
    }

    public String fight(String otherBoyName){
        return "boy "+ otherBoyName;
    }
}
