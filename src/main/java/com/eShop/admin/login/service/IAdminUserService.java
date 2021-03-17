package com.eShop.admin.login.service;

import com.eShop.commons.bean.User;

import java.util.List;

public interface IAdminUserService {
    User findUserByLogin(User user);
}