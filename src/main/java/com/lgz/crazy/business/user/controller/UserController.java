package com.lgz.crazy.business.user.controller;

import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.business.user.service.UserService;
import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.utils.CheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/toLogin")
    public ModelAndView toLogin(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Res login(String loginName,String passwd, HttpServletRequest request, HttpSession session){
        if(StringUtils.isBlank(passwd)|| StringUtils.isBlank(loginName)){
            return Res.getFailedResult("用户名或密码不能为空!");
        }
        Boolean isEmail = CheckUtil.isEmail(loginName);
        Boolean isMobile = CheckUtil.isMobile(loginName);
        User user = new User();
        user.setPasswd(passwd);
        if(isEmail){
            user.setEmail(loginName);
        }else if(isMobile){
            user.setMobile(loginName);
        }else {
            return Res.getFailedResult("手机号或邮箱格式不正确！");
        }
        return userService.login(user,isMobile,request,session);
    }
    @RequestMapping("/toRegister")
    public ModelAndView toRegister(){
        return new ModelAndView("register");
    }
    //注册
    @RequestMapping("/registerUser")
    @ResponseBody
    public Res<Integer> registerUser(User user){
        Res res = Res.getFailedResult();
        try {
            String mobile = user.getMobile();
            if(StringUtils.isBlank(mobile)){
                res.setMsg("手机号不能为空!");
                return res;
            }
            String email = user.getEmail();
            if(StringUtils.isBlank(email)){
                res.setMsg("邮箱不能为空!");
                return res;
            }
            if(StringUtils.isBlank(user.getPasswd())||StringUtils.isBlank(user.getRePwd())){
                res.setMsg("密码不能为空!");
                return res;
            }
            if(!user.getPasswd().equals(user.getRePwd())){
                res.setMsg("两次密码不一致！");
                return res;
            }
            if(!CheckUtil.isMobile(mobile)){
                res.setMsg("手机号格式不正确！");
                return res;
            }
            if(!CheckUtil.isEmail(email)){
                res.setMsg("邮箱格式不正确！");
                return res;
            }
            user.setMobile(mobile.trim());
            user.setEmail(email.trim());
            user.setPasswd(user.getPasswd().trim());
            return userService.registerUser(user);
        }catch (Exception e){
            res.setMsg("注册异常");
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
}
