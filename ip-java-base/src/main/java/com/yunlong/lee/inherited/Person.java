package com.yunlong.lee.inherited;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 2/2/22 4:38 上午
 */
public class Person {

    public static String NAME = "father";

    public String speak(String msg){
        return "Person"+msg;
    }

    public String eat(String foodName){
        return "Person eat" + foodName;
    }

    public static void main(String[] args) {
        Boy boy = new Boy();
        System.out.println(boy.fight("lijie"));
        System.out.println(boy.eat("huangua"));
    }
}
