package com.lgz.crazy.business.user.service.impl;

import com.lgz.crazy.business.user.dao.UserDao;
import com.lgz.crazy.business.user.entities.LoginInfo;
import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.business.user.service.UserService;
import com.lgz.crazy.common.constant.Constant;
import com.lgz.crazy.common.constant.SessionConstant;
import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.utils.DateUtil;
import com.lgz.crazy.common.utils.EncryptUtil;
import com.lgz.crazy.common.utils.NumUtil;
import com.lgz.crazy.common.utils.UuidUitl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

/**
 * Created by lgz on 2019/2/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //查询用户
    public Res<User> getUserByCondition(User param){
        Res res = Res.getFailedResult();
        try {
            List<User> list = userDao.getUserByCondition(param);
            if(list!=null && list.size()>0){
                User user = list.get(0);
                res.setData(user);
                return Res.getSuccessResult(user,null);
            }
            res.setMsg("用户不存在");
        }catch (Exception e){
            return Res.getExceptResult("查询用户异常:"+e.getMessage());
        }
        return res;
    }

    public static void main(String[] args) throws Exception{
        String salt = UuidUitl.getSalt();
        System.out.println(salt);
        String pwdMD5 = EncryptUtil.encryptMD5("admin"+salt);
        System.out.println(pwdMD5);
    }
    //注册用户
    public Res<Integer> registerUser(User user){
        Res res = Res.getFailedResult();
        try {
            //判断是否存在该用户
            Res isExist = isExistUser(user);
            if(NumUtil.eq(isExist.getStatus(),Res.SUCCESSSTATUS)){
                res.setMsg(isExist.getMsg());
                return res;
            }
            //设置密码
            String salt = UuidUitl.getSalt();
            user.setSalt(salt);
            String pwd = user.getPasswd();
            String pwdMD5 = EncryptUtil.encryptMD5(pwd+salt);
            user.setPasswd(pwdMD5);
            //补全信息
            String now  = DateUtil.nowDateTime();
            user.setCreateTime(now);
            user.setStatus(1);

            Integer integer = userDao.registerUser(user);
            if(NumUtil.gt(integer,0)){
                return Res.getSuccessResult(integer,null);
            }
            res.setMsg("注册失败");
        }catch (Exception e){
            return Res.getExceptResult("注册用户异常:"+e.getMessage());
        }
        return res;
    }
    //登录
    @Override
    public Res<Object> login(User user,Boolean isMobile,HttpServletRequest request, HttpSession session){
        Res res = Res.getFailedResult();
        try {
            Res<User> userByCondition = getUserByCondition(user);
            if(NumUtil.eq(userByCondition.getStatus(),Res.FAILSTATUS)){
                res.setMsg("用户不存在");
                return res;
            }
            User u = userByCondition.getData();
            String salt = u.getSalt();
            String passwd = u.getPasswd();
            String newPwd = EncryptUtil.encryptMD5(user.getPasswd()+salt);
            if(passwd.equals(newPwd)){
                LoginInfo loginInfo = new LoginInfo();
                Integer loginType = isMobile?1:2;
                loginInfo.setLoginType(loginType);
                setLoginInfoParam(loginInfo,u);
                session.setAttribute(SessionConstant.USER_LOGIN_INFO,loginInfo);
                return Res.getSuccessResult(getForwardUrl(request),null);
            }
        res.setMsg("用户名或密码错误!");
        }catch (Exception e){
            return Res.getExceptResult("登录异常:"+e.getMessage());
        }
        return res;
    }
    //注销

    //退出
    @Override
    public Res<Boolean> logout(HttpSession session){
        try {
            session.removeAttribute(SessionConstant.USER_LOGIN_INFO);
            return Res.getSuccessResult(true,null);
        }catch (Exception e){
            return Res.getExceptResult("退出异常:"+e.getMessage());
        }
    }

    public Res isExistUser(User user){
        User u = new User();
        u.setMobile(user.getMobile());
        Res<User> resTemp = getUserByCondition(u);
        if(NumUtil.eq(Res.SUCCESSSTATUS,resTemp.getStatus())){
            return Res.getSuccessResult(null,"该手机号已经存在！");
        }
        User userEmail = new User();
        userEmail.setEmail(user.getEmail());
        resTemp = getUserByCondition(u);
        if(NumUtil.eq(Res.SUCCESSSTATUS,resTemp.getStatus())){
           return Res.getSuccessResult(null,"该邮箱已经存在！");
        }
        return Res.getFailedResult("该用户不存在！");
    }

    private String getForwardUrl(HttpServletRequest request) throws UnsupportedEncodingException{
        String responseForwardUrl ="/";
        String forwardUrl = StringUtils.trim(request.getParameter("forwardUrl"));
        if (StringUtils.isNotEmpty(forwardUrl)) {
            String url = new String(Base64.getDecoder().decode(forwardUrl.getBytes(Constant.DEFULT_CHARSET)));
            //进行判断


            responseForwardUrl = url;
        }
        return responseForwardUrl;
    }
    private void setLoginInfoParam(LoginInfo loginInfo,User user){
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

}
