package com.yunlong.lee.inherited;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 23/2/22 12:02 上午
 */
public class Party {
    private void happyHour(){
        Person girl = new Girl();
        System.out.println(girl.speak("english"));
    }

    public static void main(String[] args) {
        Party party = new Party();
        party.happyHour();
    }
}
