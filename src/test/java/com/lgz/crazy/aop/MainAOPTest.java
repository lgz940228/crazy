package com.lgz.crazy.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by lgz on 2019/1/17.
 */
public class MainAOPTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        MathCalculator calculator = applicationContext.getBean("calculator",MathCalculator.class);
        int div = calculator.div(4, 2);


        applicationContext.close();
    }
}
