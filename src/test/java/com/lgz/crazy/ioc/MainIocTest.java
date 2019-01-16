package com.lgz.crazy.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainIocTest {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfigIoc.class);
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        for (String str:beanDefinitionNames) {
            System.out.println(str);
        }

        Object bean = app.getBean("&fruitFactoryBean");
        if(bean != null) System.out.println("name=fruitFactoryBean,type="+bean.getClass());

        app.close();
    }
}
