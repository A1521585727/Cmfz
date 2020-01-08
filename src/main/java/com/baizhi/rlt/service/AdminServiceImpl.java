package com.baizhi.rlt.service;

import com.baizhi.rlt.annotation.LogAnnotation;
import com.baizhi.rlt.dao.AdminDao;
import com.baizhi.rlt.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    @LogAnnotation("登陆的查一个")
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Admin selectOne(Admin admin) {
        Admin admin1 = adminDao.selectOne(admin);
        return admin1;
    }
}
