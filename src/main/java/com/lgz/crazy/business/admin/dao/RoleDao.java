package com.lgz.crazy.business.admin.dao;

import com.lgz.crazy.business.user.entities.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lgz on 2019/3/19.
 */
@Mapper
@Repository
public interface RoleDao {

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<SysRole> selectRoleList(SysRole role);
}
