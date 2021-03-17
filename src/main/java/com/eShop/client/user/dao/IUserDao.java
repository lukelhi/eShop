package com.eShop.client.user.dao;

import com.eShop.commons.bean.OrderItem;
import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
    int insertUser(User user);

    int activeUser(String activeCode);

    User selectEmail(String email);

    User selectUsername(String username);

    User selectUserByLogin(User user);

    int updateUser(User user);

    void updatePnum(OrderItem item);

    void deleteOrder(String id);

    void deleteOrderItem(String id);
}
