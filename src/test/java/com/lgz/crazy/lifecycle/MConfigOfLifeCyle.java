package com.lgz.crazy.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lgz on 2019/1/16.
 */
@Configuration
@ComponentScan("com.lgz.crazy.lifecycle")
public class MConfigOfLifeCyle {
    @Bean(initMethod = "initMethod",destroyMethod = "destroyMetod")
    public Car car(){
        Car car = new Car();
        car.setName("carName");
        return car;
    }
}
