package com.lgz.crazy.business.dept.service;


import com.lgz.crazy.business.dept.entities.Dept;

import java.util.List;

/**
 * Created by lgz on 2019/2/13.
 */
public interface DeptService {

    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}
