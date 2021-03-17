package com.eShop.client.order.dao;

import com.eShop.commons.bean.Order;
import com.eShop.commons.bean.OrderItem;
import com.eShop.commons.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    void insertOrder(Order order);

    void insertItem(OrderItem item);

    void updateProductNum(OrderItem item);

    void updatePayState(String orderId);

    List<Order> findOrderByUser(User user);

    List<OrderItem> findOrderItemById(String id);
}
