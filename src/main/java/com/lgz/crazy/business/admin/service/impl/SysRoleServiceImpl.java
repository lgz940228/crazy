package com.lgz.crazy.business.admin.service.impl;

import com.lgz.crazy.business.admin.dao.RoleDao;
import com.lgz.crazy.business.admin.service.SysRoleService;
import com.lgz.crazy.business.user.entities.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lgz on 2019/3/19.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleDao.selectRoleList(role);
    }
}
