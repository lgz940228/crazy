package com.lgz.crazy.business.admin.service;

import com.lgz.crazy.business.user.entities.SysRole;

import java.util.List;

/**
 * Created by lgz on 2019/3/19.
 */
public interface SysRoleService {

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<SysRole> selectRoleList(SysRole role);
    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    public int updateRule(SysRole role);
    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public SysRole selectRoleById(Long roleId);


}
