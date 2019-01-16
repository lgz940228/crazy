package com.lgz.crazy.ioc;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;


/**
 * @bean
 * 自定义Filter
 * @scope
 * @Lazy 只针对单实例
 *@conditonal
 *
 */
@Configurable
//1
/*@ComponentScan(value = "com.lgz.crazy.ioc",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})*/
/*@ComponentScan(value = "com.lgz.crazy.ioc",useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})*/
//3
//id 默认com.lgz.crazy.ioc.Color
//@Import({Color.class})
//implements ImportSelector 将String[] selectImports(AnnotationMetadata var1);返回的全类名 实例 注册到容器
//@Import(MyImportSelector.class)
//implements ImportBeanDefinitionRegistrar 手动调用 beanDefinitionRegistry.registerBeanDefinition(String var1, BeanDefinition var2)；
//@Import(MyImportBeanDefinitionRegistrar.class)
public class MainConfigIoc{
    //2
    /*@Bean
    public Person person01(){
        return new Person("lisi",20);
    }*/
    //4
    //FactoryBean<Fruit>
    // 默认获取的是工厂bean调用getObject创建的对象
    // 要获取factoryBean本身，在id前加&

    @Bean
    public FruitFactoryBean fruitFactoryBean(){
        return new FruitFactoryBean();
    }
}
