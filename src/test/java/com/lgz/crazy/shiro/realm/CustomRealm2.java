package com.lgz.crazy.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by lgz on 2019/3/1.
 */
public class CustomRealm2 extends AuthorizingRealm{
    @Override
    public void setName(String name) {
        super.setName("CustomRealm");
    }

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //第一步从token中取出身份信息
        String userCode = (String)authenticationToken.getPrincipal();
        //第二步根据用户输入的userCode从数据库中查询
        //...
        //模拟数据库的密码
        String pwd = "f3694f162729b7d0254c6e40260bf15c";
        //从数据库获取盐
        String salt = "qwerty";

        //如果查询不到返回null
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode,pwd, ByteSource.Util.bytes(salt),this.getName());
        //如果查询到返回AuthenticationInfo
        return simpleAuthenticationInfo;
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
