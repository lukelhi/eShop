package com.eShop.admin.orders.service;

import com.eShop.commons.bean.Order;

import java.util.List;

public interface AdminOrdersService {
    List findOrders();
    Order findOrderById(String id);
}
