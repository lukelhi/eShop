package com.eShop.admin.orders.dao;

import com.eShop.commons.bean.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminOrdersDao {
    List selectOrders();
    Order selectOrderById(String id);
    List<Order> selectOrdersByExample(Order order);
    void deleteOrder(String id);
}
