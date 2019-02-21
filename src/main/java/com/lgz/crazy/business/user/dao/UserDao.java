package com.lgz.crazy.business.user.dao;

import com.lgz.crazy.business.user.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lgz on 2019/2/20.
 */
@Mapper
@Repository
public interface UserDao {

    List<User> getUserByCondition(User user);

    Integer registerUser(User user);
}
