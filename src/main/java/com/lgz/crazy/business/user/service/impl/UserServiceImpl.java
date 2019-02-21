package com.lgz.crazy.business.user.service.impl;

import com.lgz.crazy.business.user.dao.UserDao;
import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.business.user.service.UserService;
import com.lgz.crazy.common.constant.Constant;
import com.lgz.crazy.common.constant.SessionConstant;
import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.utils.DateUtil;
import com.lgz.crazy.common.utils.EncryptUtil;
import com.lgz.crazy.common.utils.NumUtil;
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
                user.setSalt(null);
                user.setPasswd(null);
                res.setData(user);
                return Res.getSuccessResult(user);
            }
            res.setMsg("用户不存在");
        }catch (Exception e){
            return Res.getExceptResult("查询用户异常:"+e.getMessage());
        }
        return res;
    }
    //注册用户
    public Res<Integer> registerUser(User user){
        Res res = Res.getFailedResult();
        try {
            //判断是否存在该用户
            if(isExistUser(user)){
                res.setMsg("该用户已经存在");
                return res;
            }
            //设置密码
            String salt = EncryptUtil.getSalt();
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
                return Res.getSuccessResult(integer);
            }
            res.setMsg("注册失败");
        }catch (Exception e){
            return Res.getExceptResult("注册用户异常:"+e.getMessage());
        }
        return res;
    }
    //登录
    @Override
    public Res<Object> login(User user,HttpServletRequest request, HttpSession session){
        Res res = Res.getFailedResult();
        try {
            Res<User> userByCondition = getUserByCondition(user);
            if(NumUtil.eq(userByCondition.getStatus(),Res.FAILSTATUS)){
                res.setMsg("用户不存在");
                return res;
            }
            User u = userByCondition.getData();
            String salt = u.getSalt();
            String passwd = user.getPasswd();
            String newPwd = EncryptUtil.encryptMD5(passwd+salt);
            if(passwd.equals(newPwd)){
                u.setSalt(null);
                u.setPasswd(null);
                session.setAttribute(SessionConstant.USER_LOGIN_INFO,u);
                return Res.getSuccessResult(getForwardUrl(request));
            }
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
            return Res.getSuccessResult(true);
        }catch (Exception e){
            return Res.getExceptResult("退出异常:"+e.getMessage());
        }
    }

    public Boolean isExistUser(User user){
        Res<User> userByUName = getUserByCondition(user);
        if(NumUtil.eq(Res.SUCCESSSTATUS,userByUName.getStatus())){
            return true;
        }
        return false;
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


}
