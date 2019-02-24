package com.lgz.crazy.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by lgz on 2019/1/16.
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //开启路径后缀匹配
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }

    /*@Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("forward:/default.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }*/

    @Bean
    public ServletRegistrationBean servletRegistrationBean1(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
        servletServletRegistrationBean.addUrlMappings("*.do");
        //servletServletRegistrationBean.addUrlMappings("*.html");
        servletServletRegistrationBean.addUrlMappings("*.css");
        servletServletRegistrationBean.addUrlMappings("*.js");
        servletServletRegistrationBean.addUrlMappings("*.png");
        servletServletRegistrationBean.addUrlMappings("*.gif");
        servletServletRegistrationBean.addUrlMappings("*.ico");
        servletServletRegistrationBean.addUrlMappings("*.jpeg");
        servletServletRegistrationBean.addUrlMappings("*.jpg");
        servletServletRegistrationBean.addUrlMappings("*.otf");
        servletServletRegistrationBean.addUrlMappings("*.eot");
        servletServletRegistrationBean.addUrlMappings("*.svg");
        servletServletRegistrationBean.addUrlMappings("*.ttf");
        servletServletRegistrationBean.addUrlMappings("*.woff");
        servletServletRegistrationBean.addUrlMappings("*.woff2");
        return servletServletRegistrationBean;
    }
}
