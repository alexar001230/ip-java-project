package com.yunlong.lee.designPattern;

/**
 * @author lijie
 * @version 1.0
 * @description 双重锁检查单例模式
 * @date 12/2/22 12:33 上午
 */
public class DclSingleton {

    private volatile static DclSingleton singleton = null;

    public DclSingleton getSingleton(){
        if(null == singleton){
            /**
             *
             11 monitorenter
             12 aconst_null
             13 getstatic #2 <com/yunlong/lee/designPattern/DclSingleton.singleton : Lcom/yunlong/lee/designPattern/DclSingleton;>
             16 if_acmpne 29 (+13)
             19 new #3 <com/yunlong/lee/designPattern/DclSingleton>
             22 dup
             23 invokespecial #4 <com/yunlong/lee/designPattern/DclSingleton.<init> : ()V>
             26 putstatic #2 <com/yunlong/lee/designPattern/DclSingleton.singleton : Lcom/yunlong/lee/designPattern/DclSingleton;>
             29 aload_1
             30 monitorexit
             31 goto 39 (+8)
             34 astore_2
             35 aload_1
             36 monitorexit
             */
            synchronized (DclSingleton.class){
                if(null == singleton){
                    singleton = new DclSingleton();
                }
            }
        }
        return singleton;
    }
}
