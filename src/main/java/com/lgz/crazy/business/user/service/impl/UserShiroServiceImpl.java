package com.lgz.crazy.business.user.service.impl;

import com.lgz.crazy.business.user.dao.UserShiroDao;
import com.lgz.crazy.business.user.entities.UserShior;
import com.lgz.crazy.business.user.service.UserShiroService;
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
    public List<UserShior> getUserByCondition(String loginName) {
        List<UserShior> userByCondition = userShiroDao.getUserByCondition(loginName);
        System.out.println("aaa"+"vvv");
        return userByCondition;
    }
}
