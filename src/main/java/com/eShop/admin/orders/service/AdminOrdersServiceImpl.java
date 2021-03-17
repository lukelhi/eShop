package com.eShop.admin.orders.service;

import com.eShop.admin.orders.dao.AdminOrdersDao;
import com.eShop.commons.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminOrdersServiceImpl implements AdminOrdersService{
    @Resource
    AdminOrdersDao adminOrdersDao;

    @Override
    public List findOrders() {
        return adminOrdersDao.selectOrders();
    }

    @Override
    public Order findOrderById(String id) {
        return adminOrdersDao.selectOrderById(id);
    }
}
