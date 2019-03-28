package com.lgz.crazy.shiro;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lgz on 2019/3/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTest {

    /**
     * 1.通过ini配置文件创建 securityManager
     * 2.调用subject.login方法提交认证
     * 3.securityManager进行认证，securityManager通过authenticator最终由ModularRealmAuthenticator进行认证
     * 4.ModularRealmAuthenticator调用IniRealm去ini配置文件中查询用户信息
     * 5.IniRealm根据输入的token（UsernamePasswordToken）从shiro-ini查询用户信息，根据账号查询用户信息
     *      如果查询到用户信息，就给ModularRealmAuthenticator返回用户信息
     *      如果查询不到，就给ModularAuthenticator返回null
     * 6.ModularRealmAuthenticator接收IniRealm返回Authentication认证信息
     *      如果返回的认证信息是null,ModularRealmAuthenticator抛出异常
     *      如果返回的认证信息不是null（说明inirealm找到了用户），对IniRealm返回的密码（在ini文件中存在）
     *      和token中的密码进行对比，如果不一致抛出异常
     */
   /* @Test
    public void loginAndLogoutTest(){
        //创建securityManger工厂，通过ini配置文件创建securityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");

        //创建securityManager
        SecurityManager securityManager = factory.getInstance();

        //将securityManager设置当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        //从SecurityUtils中创建一个subject
        Subject subject = SecurityUtils.getSubject();

        //在认证提交前准备token(令牌)
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsa","11111");
        //org.apache.shiro.authc.IncorrectCredentialsException 密码不正确
        //org.apache.shiro.authc.UnknownAccountException 用户名不存在

        //执行认证提交
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过:"+authenticated);
    }

    @Test
    public void customRealmTest(){
        //创建securityManger工厂，通过ini配置文件创建securityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //创建securityManager
        SecurityManager securityManager = factory.getInstance();

        //将securityManager设置当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        //从SecurityUtils中创建一个subject
        Subject subject = SecurityUtils.getSubject();

        //在认证提交前准备token(令牌)
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsa","111111");
        //org.apache.shiro.authc.IncorrectCredentialsException 密码不正确
        //org.apache.shiro.authc.UnknownAccountException 用户名不存在

        //执行认证提交
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过:"+authenticated);
    }

    @Test
    public void customRealmMD5Test(){
        //创建securityManger工厂，通过ini配置文件创建securityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");

        //创建securityManager
        SecurityManager securityManager = factory.getInstance();

        //将securityManager设置当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        //从SecurityUtils中创建一个subject
        Subject subject = SecurityUtils.getSubject();

        //在认证提交前准备token(令牌)
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");
        //org.apache.shiro.authc.IncorrectCredentialsException 密码不正确
        //org.apache.shiro.authc.UnknownAccountException 用户名不存在

        //执行认证提交
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过:"+authenticated);
    }*/
}
