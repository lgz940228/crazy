package com.lgz.crazy.business.admin.service.impl;

import com.lgz.crazy.business.admin.dao.MenuDao;
import com.lgz.crazy.business.admin.service.SysMenuService;
import com.lgz.crazy.business.user.entities.SysMenu;
import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.common.core.domain.Ztree;
import com.lgz.crazy.common.utils.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgz on 2019/3/20.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private MenuDao menuDao;

    /**
     * 加载角色菜单列表树
     */
    @Override
    public List<Ztree> roleMenuTreeData(SysRole role)
    {
        Long roleId = role.getId().longValue();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysMenu> menuList = menuDao.selectMenuAll();
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleMenuList = menuDao.selectMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else
        {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysMenu> menuList, List<String> roleMenuList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleMenuList);
        for (SysMenu menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getId().longValue());
            ztree.setpId(Long.parseLong(menu.getParentId()));
            ztree.setName(transMenuName(menu, roleMenuList, permsFlag));
            ztree.setTitle(menu.getMenuName());
            if (isCheck)
            {
                ztree.setChecked(roleMenuList.contains(menu.getId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(SysMenu menu, List<String> roleMenuList, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getUrl() + "</font>");
        }
        return sb.toString();
    }
}
