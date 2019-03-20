package com.lgz.crazy.business.admin.controller;

import com.lgz.crazy.business.admin.service.SysMenuService;
import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.common.core.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgz on 2019/3/20.
 */
@Controller
@RequestMapping("/api/menu/")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 加载角色菜单列表树
     */
    @RequestMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Ztree> roleMenuTreeData(SysRole role) {
        List<Ztree> ztrees = new ArrayList<>();
        try{
            ztrees = sysMenuService.roleMenuTreeData(role);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ztrees;
    }
}
