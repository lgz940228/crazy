package com.lgz.crazy.business.user.dao;

import com.lgz.crazy.business.user.entities.SysMenu;
import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.business.user.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lgz on 2019/3/6.
 */
@Mapper
@Repository
public interface UserShiroDao {

    List<User> getUserByCondition(User user);

    Integer registerUser(User user);

    List<SysRole> queryRole(@Param("userId") String userId, @Param("available") String available);

    List<SysMenu> queryMenu(@Param("roleIds") List<String> roleIds,@Param("visible") String visible,
                           @Param("menuType") String menuType, @Param("parentId") String parentId);
}
