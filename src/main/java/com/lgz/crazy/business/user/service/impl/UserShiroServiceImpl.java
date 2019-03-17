package com.lgz.crazy.business.user.service.impl;

import com.lgz.crazy.business.user.dao.UserShiroDao;
import com.lgz.crazy.business.user.entities.SysMenu;
import com.lgz.crazy.business.user.entities.SysRole;
import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.business.user.service.UserShiroService;
import com.lgz.crazy.business.user.util.UserUtil;
import com.lgz.crazy.common.entities.Res;
import com.lgz.crazy.common.utils.DateUtil;
import com.lgz.crazy.common.utils.collection.CollectionUtil;
import com.lgz.crazy.common.utils.num.NumUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lgz on 2019/3/6.
 */
@Service
public class UserShiroServiceImpl implements UserShiroService{

    @Autowired
    private UserShiroDao userShiroDao;

    @Override
    public Res<List<User>> getUserByCondition(String userName) {
        Res res = Res.getFailedResult();
        try {
            Res checkUserName = checkUserName(userName);
            if(!Res.isSuc(checkUserName))return checkUserName;
            Integer userType = UserUtil.getUserType(userName);
            User user = new User();
            userName = userName.trim();

            if(NumUtil.eq(userType,1)){
                user.setMobile(userName);
            }
            if(NumUtil.eq(userType,2)){
                user.setEmail(userName);
            }
            if(NumUtil.eq(userType,3)){
                user.setUserName(userName);
            }
            List<User> userByCondition = userShiroDao.getUserByCondition(user);
            if(CollectionUtil.isEmpty(userByCondition)){
                res.setMsg("用户不存在");
                return res;
            }
            return Res.getSuccessResult(userByCondition);

        }catch (Exception e){
            e.printStackTrace();
            res.setMsg("用户查询异常");
        }
        return res;
    }

    @Override
    public Res<Boolean> registerUser(User user) {
        Res res = Res.getFailedResult();
        try {
            user.setCreateTime(DateUtil.Timestamp2DateTime(System.currentTimeMillis()));
            user.setStatus(1);
            Integer integer = userShiroDao.registerUser(user);
            if(integer != null && integer > 0){
                return Res.getSuccessResult(true);
            }
            res.setData(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Res<List<SysRole>> queryRole(String id,String available) {
       List<SysRole> sysRoleRes = userShiroDao.queryRole(id, available);
       return Res.getSuccessResult(sysRoleRes);
    }

    @Override
    public Res<List<SysMenu>> queryMenu(List<String> roleIds,String visible, String menuType,String parentId) {
        List<SysMenu> sysMenus = userShiroDao.queryMenu(roleIds, visible, menuType, parentId);
        return Res.getSuccessResult(sysMenus);
    }

    private Res checkUserName(String userName){
        Res res = Res.getFailedResult();
        if(StringUtils.isBlank(userName)){
            res.setMsg("用户名不能为空");
            return res;
        }
        Integer userType = UserUtil.getUserType(userName);
        if(NumUtil.eq(0,userType)){
            res.setMsg("用户名不合格");
            return res;
        }
        return Res.getSuccessResult();
    }


}


