package com.lgz.crazy.ioc;

import org.springframework.beans.factory.FactoryBean;

public class FruitFactoryBean implements FactoryBean<Fruit> {
    @Override
    public Fruit getObject() throws Exception {
        return new Fruit();
    }

    @Override
    public Class<?> getObjectType() {
        return Fruit.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
