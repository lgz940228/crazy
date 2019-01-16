package com.lgz.crazy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lgz on 2019/1/16.
 */
@Configuration
public class MConfigOfLifeCyle {
    @Bean(initMethod = "initMethod",destroyMethod = "destroyMetod")
    public Car car(){
        Car car = new Car();
        car.setName("carName");
        return car;
    }
}
