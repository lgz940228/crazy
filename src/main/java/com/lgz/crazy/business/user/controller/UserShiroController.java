package com.lgz.crazy.business.user.controller;

import com.lgz.crazy.business.user.entities.UserShior;
import com.lgz.crazy.business.user.service.UserShiroService;
import com.lgz.crazy.common.entities.Res;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lgz on 2019/3/6.
 */
@Controller
@RequestMapping("/api/shior")
public class UserShiroController {
    @Autowired
    private UserShiroService userShiroService;
    @RequestMapping("/user")
    @ResponseBody
    public List<UserShior> getUserByCondition(String loginName){
        loginName="15510498910";
        return userShiroService.getUserByCondition(loginName);
    }

    @RequestMapping("login")
    @ResponseBody
    public Res login(String userCode,String passwd){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                "15510498910",
                "111111");
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        }catch (Exception e){
            e.printStackTrace();
            return Res.getFailedResult(e.getMessage());
        }
        return Res.getSuccessResult();
    }

    @RequestMapping("test")
    @ResponseBody

    public Res test(String userCode,String passwd){
        Subject subject = SecurityUtils.getSubject();
        boolean aaa = subject.isPermitted("aaa");
        System.out.println("isPermitted="+aaa);
        System.out.println("aaa");
        System.out.println("bbb");
        return Res.getSuccessResult();
    }

    @ResponseBody
    @RequestMapping("/403")
    public String fo(){
        return "403";
    }

}
