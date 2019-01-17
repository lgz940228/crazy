package com.lgz.crazy.lifecycle;


import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * Created by lgz on 2019/1/17.
 */
@Component
public class InstantiationAwareBeanPostProcessorImpl implements InstantiationAwareBeanPostProcessor{

    public InstantiationAwareBeanPostProcessorImpl(){
        super();
        System.out.println("【4.实例化InstantiationAwareBeanPostProcessorImpl】");
    }

    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("【5.InstantiationAwareBeanPostProcessor接口】调用postProcessBeforeInstantiation方法");
        return null;
    }

    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("【8.InstantiationAwareBeanPostProcessor接口】调用postProcessAfterInstantiation方法");
        return true;
    }

    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
        System.out.println("【9.1.InstantiationAwareBeanPostProcessor接口】调用postProcessProperties方法");
        return null;
    }

    public PropertyValues postProcessPropertyValues(
            PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("【9.2.InstantiationAwareBeanPostProcessor接口】调用postProcessPropertyValues方法");
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【13.InstantiationAwareBeanPostProcessor接口】调用postProcessBeforeInitialization方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【18.InstantiationAwareBeanPostProcessor接口】调用postProcessAfterInitialization方法");
        return bean;
    }
}
