package com.eShop.admin.user.service;

import com.eShop.admin.user.dao.AdminUsersDao;
import com.eShop.commons.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AdminUsersServiceImpl implements AdminUserService {
    @Resource
    AdminUsersDao adminUsersDao;
    @Override
    public List<User> findUsers() {
        return adminUsersDao.selectUsers();
    }

    @Override
    public User findUserById(Integer id) {
        return adminUsersDao.selectUserById(id);
    }

    @Override
    public void addUser(User user) {
        user.setActiveCode("default");
        user.setRegistTime(new Date());
        user.setState(1);
        adminUsersDao.insertUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return adminUsersDao.selectUserByUsername(username);
    }
}
