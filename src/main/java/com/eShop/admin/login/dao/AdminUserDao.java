package com.eShop.admin.login.dao;

import com.eShop.commons.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminUserDao {
    User selectUserByLogin(User user);
}
