package com.yunlong.lee;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 27/2/22 4:07 上午
 */
public class SpringBeanCreateTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(
                        "spring/spring-beans.xml");
        HelloService helloService =
                applicationContext.getBean(HelloService.class);
        System.out.println(helloService.sayHello("springBean-create"));
    }
}
