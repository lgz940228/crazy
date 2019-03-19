package com.lgz.crazy.business.admin.controller;

import com.lgz.crazy.business.admin.entities.Menu;
import com.lgz.crazy.business.admin.service.AdminService;
import com.lgz.crazy.business.user.entities.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/ruoyi/index");
        List<SysRole> sysRoles = adminService.obtionRoles();
        List<String> roleIds = new ArrayList<>();
        for (SysRole role :sysRoles){
            roleIds.add(String.valueOf(role.getId()));
        }
        List<Menu> menus = adminService.obtionMenus(roleIds,"0");
        mv.addObject("menus",menus);
        return mv;
    }

    @RequestMapping("main")
    public ModelAndView main(){
        ModelAndView mv = new ModelAndView("/ruoyi/main");
        return mv;
    }
}
