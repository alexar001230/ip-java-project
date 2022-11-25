package com.yunlong.lee;

import org.springframework.stereotype.Component;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 27/2/22 4:08 上午
 */
@Component
public class HelloImpl implements HelloService{
    @Override
    public String sayHello(String msg) {
        return msg;
    }
}
