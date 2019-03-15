package com.lgz.crazy.config;

import com.google.code.kaptcha.util.Config;
import com.lgz.crazy.common.utils.PropertiesReader;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by lgz on 2019/2/22.
 */
@Component("kaptchaConfig")
public class KaptchaConfig extends Config {

    private static final Properties PROPERTIES_KAPTCHA = PropertiesReader.getProperties("kaptcha");

    /**
     * 加载资源
     */
    public KaptchaConfig() {
        super(PROPERTIES_KAPTCHA);
    }
}
