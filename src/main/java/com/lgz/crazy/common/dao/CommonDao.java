package com.lgz.crazy.common.dao;

import com.lgz.crazy.common.entities.SysDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lgz on 2019/3/19.
 */
@Mapper
@Repository
public interface CommonDao {

    List<SysDictionary> querySysDictionary(@Param("status") String status, @Param("dictType") String dictType);

}
