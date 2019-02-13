package com.lgz.crazy.business.dept.service.impl;

import com.lgz.crazy.business.dept.dao.DeptDao;
import com.lgz.crazy.business.dept.entities.Dept;
import com.lgz.crazy.business.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lgz on 2019/2/13.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    public DeptDao deptDao;

    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept findById(Long deptId) {
        return deptDao.findById(deptId);
    }

    @Override
    public List<Dept> findAll() {
        return deptDao.findAll();
    }
}
