package com.yunlong.lee.designPattern;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 8/3/22 12:58 上午
 */
public class DclSingleton2 {
    private volatile static DclSingleton2 dclSingleton2;

    public DclSingleton2 getDclSingleton2() {
        if (null != dclSingleton2) {
            synchronized (DclSingleton2.class) {
                if (null != dclSingleton2) {
                    dclSingleton2 = new DclSingleton2();
                }
            }
        }
        return dclSingleton2;
    }
}
