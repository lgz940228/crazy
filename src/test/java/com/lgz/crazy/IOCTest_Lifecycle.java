package com.lgz.crazy;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by lgz on 2019/1/16.
 */
public class IOCTest_Lifecycle {

    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MConfigOfLifeCyle.class);
        System.out.println("容器创建完成");
        applicationContext.close();
        System.out.println("容器关闭完成");
    }
    
}
