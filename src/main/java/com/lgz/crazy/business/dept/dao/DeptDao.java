package com.lgz.crazy.business.dept.dao;

/**
 * Created by lgz on 2019/2/13.
 */

import com.lgz.crazy.business.dept.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept findById(Long deptId);

    public List<Dept> findAll();
}

