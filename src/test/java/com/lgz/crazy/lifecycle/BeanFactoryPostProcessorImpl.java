package com.lgz.crazy.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by lgz on 2019/1/17.
 */
@Component
public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {
    public BeanFactoryPostProcessorImpl(){
        super();
        System.out.println("【1.实例化BeanFactoryPostProcessorImpl】");
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("【2.BeanFactoryPostProcessor接口实现类】调用postProcessBeanFactory方法");
    }
}
