package com.eShop.admin.user.service;

import com.eShop.admin.user.dao.AdminUsersDao;
import com.eShop.commons.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminUserServiceImp implements AdminUserService { //AdminUserServiceImp 防止bean重复
    @Resource
    AdminUsersDao adminUsersDao;
    @Override
    public List<User> findUsers() {
        return adminUsersDao.selectUsers();
    }
}
