package com.lgz.crazy.config.shiro;

import com.lgz.crazy.business.user.entities.UserShior;
import com.lgz.crazy.business.user.service.UserShiroService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by lgz on 2019/3/5.
 * https://www.cnblogs.com/asker009/p/9471712.html
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserShiroService userShiroService;

    @Override
    public String getName() {
        return "MyShiroRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserShior UserShior = (UserShior)principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("role1");
        for(String str : UserShior.getPerm()){
            simpleAuthorizationInfo.addStringPermission(str);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userCode = (String)authenticationToken.getPrincipal();
        if(userCode == null) return null;
        List<UserShior> userInfo = userShiroService.getUserByCondition(userCode);
        if(userInfo == null || userInfo.size()<0) return null;
        UserShior userShior = userInfo.get(0);
        /*Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName*/
        SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(userShior,userShior.getPasswd(), new SimpleByteSource(userShior.getSalt()),getName());
        return simpleAuthorizationInfo;
    }
}
