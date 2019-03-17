package com.lgz.crazy.business.admin.service;

import com.lgz.crazy.business.admin.entities.Menu;
import com.lgz.crazy.business.user.entities.LoginInfo;
import com.lgz.crazy.business.user.entities.SysMenu;
import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.business.user.service.UserShiroService;
import com.lgz.crazy.common.entities.Res;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserShiroService userShiroService;

    public List<SysRole> obtionRoles(){
        try {
            LoginInfo loginInfo = (LoginInfo) SecurityUtils.getSubject().getPrincipal();
            String userId = String.valueOf(loginInfo.getId());
            Res<List<SysRole>> listRes = userShiroService.queryRole(userId, "1");
            return listRes.getData();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Menu> obtionMenus(List<String> roles, String parentId){
        List<Menu> menus = new ArrayList<>();
        Res<List<SysMenu>> sysMenus = userShiroService.queryMenu(roles, "1", "M", parentId);
        for(SysMenu temp : sysMenus.getData()){
            Menu menu = new Menu();
            menu.setIcon(temp.getIcon());
            menu.setId(String.valueOf(temp.getId()));
            menu.setMenuName(temp.getMenuName());
            menu.setUrl(temp.getUrl());
            if("0".equals(temp.getIsChild())){
                menu.setChildren(obtionMenus(roles,String.valueOf(temp.getId())));
            }
            menus.add(menu);
        }
        return menus;
    }
}
