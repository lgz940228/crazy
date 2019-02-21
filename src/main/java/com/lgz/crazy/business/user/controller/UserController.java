package com.lgz.crazy.business.user.controller;

import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.business.user.service.UserService;
import com.lgz.crazy.common.entities.Res;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by lgz on 2019/2/20.
 */
@Controller
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Res login(User user, HttpServletRequest request, HttpSession session){
        if(StringUtils.isBlank(user.getPasswd())|| StringUtils.isBlank(user.getLoginName())){
            return Res.getFailedResult("用户名或密码不能为空!");
        }
        return userService.login(user,request,session);
    }

    //注册
    @RequestMapping("/registerUser")
    @ResponseBody
    public Res<Integer> registerUser(User user){
        Res res = Res.getFailedResult();
        if(StringUtils.isBlank(user.getLoginName())){
            res.setMsg("用户名不能为空!");
            return res;
        }
        if(StringUtils.isBlank(user.getPasswd())){
            res.setMsg("密码不能为空!");
            return res;
        }
        try {
            return userService.registerUser(user);
        }catch (Exception e){
            res.setMsg("注册失败");
        }
        return res;
    }

    //退出
    @RequestMapping("/logout")
    public Res<Boolean> logout(HttpSession session){
        return userService.logout(session);
    }

    //重置密码

    //注销账户



    @RequestMapping("getUserByCondition")
    @ResponseBody
    public Res<User> getUserByCondition(User user){
        Res res = Res.getFailedResult();
        if(checkParamOne(user)){
            res.setMsg("参数不正确!");
            return res;
        }
        return userService.getUserByCondition(user);
    }

    private Boolean checkParamOne(User user){
        if(user.getLoginName()==null&&user.getMobile()==null&&user.getEmail()==null){
            return true;
        }
        return false;
    }

    @RequestMapping("/isExistUser")
    @ResponseBody
    public Res<Boolean> isExistUser(User user){
        Res res = Res.getFailedResult();
        if(checkParamOne(user)){
            res.setMsg("参数不正确!");
            return res;
        }
        Boolean existUser = userService.isExistUser(user);
        if(existUser){
            return Res.getSuccessResult(true);
        }
        res.setData(false);
        return res;
    }
}
