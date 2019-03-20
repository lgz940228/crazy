package com.lgz.crazy.business.admin.service;

import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.common.core.domain.Ztree;

import java.util.List;

/**
 * Created by lgz on 2019/3/20.
 */
public interface SysMenuService {
    /**
     * 加载角色菜单列表树
     */
    public List<Ztree> roleMenuTreeData(SysRole role);
}
