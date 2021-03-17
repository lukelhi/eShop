package com.eShop.admin.user.dao;

import com.eShop.commons.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUsersDao {
    List<User> selectUsers();
}
