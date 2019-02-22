package com.lgz.crazy.config.Component;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lgz on 2019/2/22.
 */
@Component("sZKaptcha")
public class SZKaptcha extends DefaultKaptcha {

    @Resource(name = "kaptchaConfig")
    public void setConfig(Config config) {
        super.setConfig(config);
    }
}
