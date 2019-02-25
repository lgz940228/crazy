package com.lgz.crazy.business.user.service;

import com.lgz.crazy.business.user.entities.User;
import com.lgz.crazy.common.entities.Res;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lgz on 2019/2/20.
 */
public interface UserService {

    Res<User> getUserByCondition(User param);

    Res<Integer> registerUser(User user);

    Res isExistUser(User user);

    Res<Object> login(User user,Boolean isMobile, HttpServletRequest request, HttpSession session);

    Res<Boolean> logout(HttpSession session);
}
