package com.lgz.crazy.common.utils.common;

import com.lgz.crazy.business.user.entities.LoginInfo;
import com.lgz.crazy.common.utils.num.NumUtil;
import com.lgz.crazy.config.shiro.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * shiro 工具类
 * 
 */
public class ShiroUtils
{
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }

    public static Session getSession()
    {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout()
    {
        getSubject().logout();
    }

    public static LoginInfo getSysUser()
    {
        LoginInfo loginInfo = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            loginInfo = new LoginInfo();
            BeanUtils.copyBeanProp(loginInfo, obj);
        }
        return loginInfo;
    }

    public static void setSysUser(LoginInfo user)
    {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    public static void clearCachedAuthorizationInfo()
    {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }

    public static Long getUserId()
    {
        return getSysUser().getId();
    }

    public static String getLoginName()
    {
        LoginInfo sysUser = getSysUser();
        Integer loginType = sysUser.getLoginType();
        String loginName;
        if(NumUtil.eq(loginType,1)){
            loginName =sysUser.getMobile();
        }else if(NumUtil.eq(loginType,2)){
            loginName = sysUser.getEmail();
        }else if(NumUtil.eq(loginType,3)){
            loginName = sysUser.getUserName();
        }else {
            loginName = "";
        }
        return loginName;
    }

    public static String getIp()
    {
        return getSubject().getSession().getHost();
    }

    public static String getSessionId()
    {
        return String.valueOf(getSubject().getSession().getId());
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt()
    {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }
}
