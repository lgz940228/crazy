package com.lgz.crazy.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lgz on 2019/3/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorizationTest {
    @Test
    public void authorization(){
        //创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        //创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager设置到系统运行环境中，和spring后将SecurityManager配置到spring容器中，一般单例管理
        SecurityUtils.setSecurityManager(securityManager);
        //创建subject
        Subject subject = SecurityUtils.getSubject();
        //执行认证
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");

        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        //基于角色
        boolean role1 = subject.hasRole("role1");
        System.out.println("subject.hasRole="+role1);
        //基于资源
        boolean permitted = subject.isPermitted("user:create");
        System.out.println("subject.isPermitted="+permitted);
    }

    @Test
    public void authorizationCusRealm(){
        //创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
        //创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //将SecurityManager设置到系统运行环境中，和spring后将SecurityManager配置到spring容器中，一般单例管理
        SecurityUtils.setSecurityManager(securityManager);
        //创建subject
        Subject subject = SecurityUtils.getSubject();
        //执行认证
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");

        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        //基于角色
        boolean role1 = subject.hasRole("role1");
        System.out.println("subject.hasRole="+role1);
        //基于资源 调用isPermitted方法会调用CustomRealm从数据库中查询的权限
        //isPermitted传入权限标识符号，判断user:create是否在CustomRealm查询到权限数据之内
        boolean permitted = subject.isPermitted("user:create");
        System.out.println("subject.isPermitted="+permitted);
    }
}
