package com.lgz.crazy.lifecycle;

import org.springframework.stereotype.Component;

/**
 * Created by lgz on 2019/1/17.
 */
@Component
public class Eye {

    /*private Dog dog;

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }*/

    public Eye(){
        System.out.println("----------------Eye");
    }
}
