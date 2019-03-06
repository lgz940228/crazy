package com.lgz.crazy.business.user.service;

import com.lgz.crazy.business.user.entities.UserShior;

import java.util.List;

/**
 * Created by lgz on 2019/3/6.
 */
public interface UserShiroService {
    List<UserShior> getUserByCondition(String loginName);
}
