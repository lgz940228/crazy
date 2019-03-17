package com.lgz.crazy.business.user.service;

import com.lgz.crazy.business.user.entities.SysMenu;
import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.common.entities.Res;

import java.util.List;

/**
 * Created by lgz on 2019/3/6.
 */
public interface UserShiroService {

    Res<List<User>> getUserByCondition(String userName);

    Res<Boolean> registerUser(User user);

    Res<List<SysRole>> queryRole(String id,String available);

    Res<List<SysMenu>> queryMenu(List<String> roleIds,String visible, String menuType,String parentId);
}
