package com.lgz.crazy.business.admin.service;

import com.lgz.crazy.business.admin.entities.Menu;
import com.lgz.crazy.business.user.entities.SysRole;

import java.util.List;

public interface AdminService {

    public List<SysRole> obtionRoles();

    public List<Menu> obtionMenus(List<String> roles, String parentId);
}
