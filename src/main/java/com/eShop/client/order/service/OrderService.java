package com.eShop.client.order.service;

import com.eShop.commons.bean.Order;
import com.eShop.commons.bean.OrderItem;
import com.eShop.commons.bean.Product;
import com.eShop.commons.bean.User;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void createOrder(Order order, Map<Product, Integer> cart);

    void paySuccess(String orderId);

    List<Order> findOrderByUser(User user);

    List<OrderItem> findOrderItemById(String id);
}
