package com.lgz.crazy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by lgz on 2019/1/16.
 */
public class Car implements InitializingBean,DisposableBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("carSetName");
        this.name = name;
    }

    public Car() {
        System.out.println("constructor start");
    }

    public void initMethod(){
        System.out.println("@Bean initMethod");
    }

    public void destroyMetod(){
        System.out.println("@Bean destroyMethod");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet");
    }

    @Override
    public void destroy(){
        System.out.println("InitializingBean destroy");
    }
}
