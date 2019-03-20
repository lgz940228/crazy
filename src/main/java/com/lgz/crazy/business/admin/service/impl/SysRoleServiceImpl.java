package com.lgz.crazy.business.admin.service.impl;

import com.lgz.crazy.business.admin.dao.RoleDao;
import com.lgz.crazy.business.admin.service.SysRoleService;
import com.lgz.crazy.business.user.entities.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRule(SysRole role)
    {
        /*// 修改角色信息
        roleDao.updateRole(role);
        // 删除角色与部门关联
        roleDao.deleteRoleDeptByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);*/
        return 0;
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public SysRole selectRoleById(Long roleId)
    {
        return roleDao.selectRoleById(roleId);
    }
}
