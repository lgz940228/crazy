package com.lgz.crazy.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by lgz on 2019/1/17.
 */
@Component
public class BeanPostProcessorImpl implements BeanPostProcessor{
    public BeanPostProcessorImpl(){
        super();
        System.out.println("【3.实例化BeanPostProcessorImpl】");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【12.BeanPostProcessor接口】调用postProcessBeforeInitialization方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【17.BeanPostProcessor接口】调用postProcessAfterInitialization方法");
        return bean;
    }
}
