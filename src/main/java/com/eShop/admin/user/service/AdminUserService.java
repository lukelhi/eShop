package com.eShop.admin.user.service;

import com.eShop.commons.bean.User;

import java.util.List;

public interface AdminUserService {
    List<User> findUsers();
    User findUserById(Integer id);
    void addUser(User user);
    User findUserByUsername(String username);
    void removeUserById(String id);
    void editUser(User user);
}
