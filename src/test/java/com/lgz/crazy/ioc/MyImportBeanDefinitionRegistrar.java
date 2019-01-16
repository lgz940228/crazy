package com.lgz.crazy.ioc;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        RootBeanDefinition aRootBeanDefinition = new RootBeanDefinition(AmericaImportBeanDefinitionRegistrar.class);
        RootBeanDefinition cRootBeanDefinition = new RootBeanDefinition(ChinaImportBeanDefinitionRegistrar.class);
        beanDefinitionRegistry.registerBeanDefinition("americaImportBeanDefinitionRegistrar",aRootBeanDefinition);
        beanDefinitionRegistry.registerBeanDefinition("cmericaImportBeanDefinitionRegistrar",cRootBeanDefinition);
    }
}
