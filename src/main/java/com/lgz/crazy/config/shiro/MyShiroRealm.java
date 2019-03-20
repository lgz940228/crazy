package com.lgz.crazy.config.shiro;

import com.lgz.crazy.business.user.entities.LoginInfo;
import com.lgz.crazy.business.user.entities.SysMenu;
import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.business.user.service.UserShiroService;
import com.lgz.crazy.business.user.util.UserUtil;
import com.lgz.crazy.common.entities.Res;
import org.apache.shiro.SecurityUtils;
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

import java.util.ArrayList;
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
        LoginInfo user = (LoginInfo)principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Res<List<SysRole>> userRoleList = userShiroService.queryRole(String.valueOf(user.getId()),null);
        List<String> roleIdList = new ArrayList<>();
        for(SysRole role : userRoleList.getData()){
            simpleAuthorizationInfo.addRole(role.getRoleKey());
            roleIdList.add(String.valueOf(role.getId()));
        }
        Res<List<SysMenu>> listRes = userShiroService.queryMenu(roleIdList, "1", null, null);
        for(SysMenu menu : listRes.getData()){
            simpleAuthorizationInfo.addStringPermission(menu.getUrl());
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String)authenticationToken.getPrincipal();
        if(userName == null) return null;
        Res<List<User>> userInfoRes = userShiroService.getUserByCondition(userName);
        if(Res.isNotSuc(userInfoRes)){
            return null;
        }
        User user = userInfoRes.getData().get(0);
        String passwd = user.getPasswd();
        String salt = user.getSalt();
        /*user.setPasswd(null);
        user.setSalt(null);*/
        LoginInfo loginInfo = new LoginInfo();
        Integer userType = UserUtil.getUserType(userName);
        loginInfo.setLoginType(userType);
        setLoginInfoParam(loginInfo,user);
        /*Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName*/
        SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(loginInfo,passwd, new SimpleByteSource(salt),getName());
        return simpleAuthorizationInfo;
    }

    private void setLoginInfoParam(LoginInfo loginInfo, User user){
        loginInfo.setId(user.getId());
        loginInfo.setBirthday(user.getBirthday());
        loginInfo.setCancelReason(user.getCancelReason());
        loginInfo.setCancelTime(user.getCancelTime());
        loginInfo.setCity(user.getCity());
        loginInfo.setCreateTime(user.getCreateTime());
        loginInfo.setEmail(user.getEmail());
        loginInfo.setImg(user.getImg());
        loginInfo.setIcon(user.getIcon());
        loginInfo.setMobile(user.getMobile());
        loginInfo.setNick(user.getNick());
        loginInfo.setPersonalizedSignature(user.getPersonalizedSignature());
        loginInfo.setResetPwdTime(user.getResetPwdTime());
        loginInfo.setRoleId(user.getRoleId());
        loginInfo.setSex(user.getSex());
        loginInfo.setTel(user.getTel());
        loginInfo.setUserName(user.getUserName());
        loginInfo.setResetPwdTime(user.getResetPwdTime());
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
