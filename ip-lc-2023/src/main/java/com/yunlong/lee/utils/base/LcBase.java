package com.yunlong.lee.utils.base;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/4/23 10:47 上午
 * @link
 */
public class LcBase {
    public void doPrint(){
        String clazzName = this.getClass().getSimpleName();
        try {
            Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
