package com.eShop.admin.user.dao;

import com.eShop.commons.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUsersDao {
    List<User> selectUsers();
    User selectUserById(Integer id);
    void insertUser(User user);
    User selectUserByUsername(String username);
}
