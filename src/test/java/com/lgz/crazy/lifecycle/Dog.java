package com.lgz.crazy.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by lgz on 2019/1/16.
 */
@Component
public class Dog implements InitializingBean,DisposableBean,BeanFactoryAware, BeanNameAware{

    private String name;

    public String getName() {
        return name;
    }
   /* @Autowired
    private Eye eye;
    public Eye getEye() {
        return eye;
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }*/

    public void setName(String name) {
        System.out.println("【7.Bean注入属性】setName方法");
        this.name = name;
    }

    public Dog() {
        System.out.println("【6.Bean构造器】");
    }

    public void initMethod(){
        System.out.println("【16.@Bean initMethod 方法】");
    }

    public void destroyMetod(){
        System.out.println("【@Bean destroyMethod 方法】");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【15.InitializingBean接口】调用afterPropertiesSet方法");
    }

    @Override
    public void destroy(){
        System.out.println("【DisposableBean接口】调用destroy方法");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("【14.@postConstruct注解】postConstruct");
    }

    @PreDestroy
    public void PreDestroy(){
        System.out.println("【@PreDestroy注解】PreDestroy");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【11.BeanFactoryAware接口】调用setBeanFactory方法");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("【10.BeanNameAware接口】调用setBeanName方法");
    }

}
