package com.lgz.crazy.business.user.controller;

import com.lgz.crazy.business.user.entities.LoginInfo;
import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.business.user.service.UserShiroService;
import com.lgz.crazy.business.user.util.UserUtil;
import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.utils.UuidUitl;
import com.lgz.crazy.common.utils.num.NumUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lgz on 2019/3/6.
 */
@Controller
@RequestMapping("/api/shiro/")
public class UserShiroController {
    @Autowired
    private UserShiroService userShiroService;

    @RequestMapping("toLogin")
    public ModelAndView toLogin(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("login")
    @ResponseBody
    public Res login(String userName, String passwd, HttpSession session){
        Res res = Res.getFailedResult();
        try {
            if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)){
                res.setMsg("用户名或者密码不能为空");
                return res;
            }
            Integer userType = UserUtil.getUserType(userName);
            if(NumUtil.eq(0,userType)){
                res.setMsg("用户名不合格");
                return res;
            }
            //shiro 登录
            //添加用户认证信息
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName.trim(), passwd.trim());
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException uae){
            return Res.getFailedResult("用户名不存在");
        }catch (IncorrectCredentialsException ice){
            return Res.getFailedResult("密码不正确");
        } catch (Exception e){
            e.printStackTrace();
            return Res.getFailedResult(e.getMessage());
        }
        LoginInfo loginInfo = (LoginInfo)SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("userLoginInfo",loginInfo);
        return Res.getSuccessResult();
    }

    @RequestMapping("/toRegister")
    public ModelAndView toRegister(){
        return new ModelAndView("register");
    }

    //注册
    @RequestMapping("/register")
    @ResponseBody
    public Res<Boolean> registerUser(String userName, String passwd, String rePasswd){
        Res res = Res.getFailedResult();
        User user = new User();
        try {
            Integer userType = UserUtil.getUserType(userName);
            if(NumUtil.eq(-1,userType)){
                res.setMsg("用户名为空");
                return res;
            }else if(NumUtil.eq(0,userType)){
                res.setMsg("用户名不合法");
                return res;
            }else if(NumUtil.eq(1,userType)){
                user.setMobile(userName.trim());
            }else if(NumUtil.eq(2,userType)){
                user.setEmail(userName.trim());
            }else if(NumUtil.eq(3,userType)){
                user.setUserName(userName.trim());
            }
            Res<List<User>> userByCondition = userShiroService.getUserByCondition(userName.trim());
            if(Res.isSuc(userByCondition)){
                res.setMsg("此用户已存在");
                return res;
            }
            if(StringUtils.isBlank(passwd)||StringUtils.isBlank(rePasswd)){
                res.setMsg("密码不能为空!");
                return res;
            }
            if(!passwd.trim().equals(rePasswd.trim())){
                res.setMsg("两次密码不一致！");
                return res;
            }
            String salt = UuidUitl.getSalt();
            Md5Hash md5Hash = new Md5Hash(passwd.trim(),salt,2);
            user.setPasswd(md5Hash.toString());
            user.setSalt(salt);
            return userShiroService.registerUser(user);
        }catch (Exception e){
            res.setMsg("注册异常");
        }
        return res;
    }

    @RequestMapping("logout")
    @ResponseBody
    public Res exit(){
        Res res = Res.getFailedResult();
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("test")
    @ResponseBody
    public Res test(String userCode,String passwd){
        /*Subject subject = SecurityUtils.getSubject();*/
       /* boolean aaa = subject.isPermitted("aaa");
        System.out.println("isPermitted="+aaa);*/
        System.out.println("aaa");
        System.out.println("bbb");
        return Res.getSuccessResult();
    }

    @RequestMapping("403")
    @ResponseBody
    public String forbid(){
        return "403";
    }

    @RequestMapping("test2")
    @ResponseBody
    public String test2(){
        return "444";
    }
    @RequestMapping("ttt")
    @ResponseBody
    public String ttt(){
        return "444";
    }

}
