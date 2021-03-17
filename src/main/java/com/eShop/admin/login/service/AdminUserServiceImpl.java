package com.eShop.admin.login.service;

import com.eShop.admin.login.dao.AdminUserDao;
import com.eShop.commons.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminUserServiceImpl implements IAdminUserService{
    @Autowired
    AdminUserDao adminUserDao;
    @Override
    public User findUserByLogin(User user) {
        return adminUserDao.selectUserByLogin(user);
    }
}
