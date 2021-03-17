package com.eShop.client.user.service;

import com.eShop.commons.bean.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    int addUser(User user, HttpServletRequest request);

    int activeUser(String activeCode);

    User findEmail(String email);

    User findUsername(String username);

    User findUserByLogin(User user);

    int modifyUser(User user);

    void removeOrderById(String id, String flag);
}
