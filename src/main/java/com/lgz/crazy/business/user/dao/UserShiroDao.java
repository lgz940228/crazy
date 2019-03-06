package com.lgz.crazy.business.user.dao;

import com.lgz.crazy.business.user.entities.UserShior;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lgz on 2019/3/6.
 */
@Mapper
@Repository
public interface UserShiroDao {

    List<UserShior> getUserByCondition(String loginName);

}
