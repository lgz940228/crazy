package com.lgz.crazy.config;

import com.google.code.kaptcha.util.Config;
import com.lgz.crazy.common.utils.SysUtil;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by lgz on 2019/2/22.
 */
@Component("kaptchaConfig")
public class KaptchaConfig extends Config {
    /**
     * 验证码配置资源名
     */
    private static final String RESNAME_KAPTCHA = "/kaptcha.properties";

    private static final Properties PROPERTIES_KAPTCHA = SysUtil.getProperties(getResourcePropertiesName());

    /**
     * 加载资源
     */
    public KaptchaConfig() {
        super(PROPERTIES_KAPTCHA);
    }

    /**
     * 读取resource配置文件绝对地址路径
     * @return
     */
    private static String getResourcePropertiesName(){
        return KaptchaConfig.class.getResource(RESNAME_KAPTCHA).getPath().replaceAll("%20", " ");
    }
}
